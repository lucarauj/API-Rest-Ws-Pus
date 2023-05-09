package com.client.ws.service.impl;

import com.client.ws.dto.PaymentProcessDto;
import com.client.ws.dto.wsraspay.CustomerDto;
import com.client.ws.dto.wsraspay.OrderDto;
import com.client.ws.dto.wsraspay.PaymentDto;
import com.client.ws.enums.UserTypeEnum;
import com.client.ws.mapper.wsraspay.PaymentMapper;
import com.client.ws.exception.BusinessException;
import com.client.ws.exception.NotFoundException;
import com.client.ws.integration.MailIntegration;
import com.client.ws.integration.WsRaspayIntegration;
import com.client.ws.mapper.wsraspay.CreditCardMapper;
import com.client.ws.mapper.wsraspay.CustomerMapper;
import com.client.ws.mapper.wsraspay.OrderMapper;
import com.client.ws.mapper.UserPaymentInfoMapper;
import com.client.ws.model.User;
import com.client.ws.model.UserPaymentInfo;
import com.client.ws.repository.SubscriptionTypeRepository;
import com.client.ws.repository.UserPaymentInfoRepository;
import com.client.ws.repository.UserRepository;
import com.client.ws.repository.UserTypeRepository;
import com.client.ws.service.PaymentInfoService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final MailIntegration mailIntegration;
    private final UserTypeRepository userTypeRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository, WsRaspayIntegration wsRaspayIntegration, MailIntegration mailIntegration, UserTypeRepository userTypeRepository, SubscriptionTypeRepository subscriptionTypeRepository) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.mailIntegration = mailIntegration;
        this.userTypeRepository = userTypeRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }
    @Override
    public Boolean process(PaymentProcessDto paymentProcessDto) {

        //Verifica usuário por id e verifica se já existe assinatura
        var userOpt = userRepository.findById(paymentProcessDto.getUserPaymentInfoDto().getUserId());
        if(userOpt.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        User user = userOpt.get();
        if(Objects.nonNull(user.getSubscriptionType())) {
            throw new BusinessException("Pagamento não pode ser processo pois usuário já possui assinatura");
        }
        Boolean successPayment = getSucessPayment(paymentProcessDto, user);

        if(Boolean.TRUE.equals(successPayment)) {

            //Salva informações de pagamento
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(paymentProcessDto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);

            var userTypeOpt = userTypeRepository.findById(UserTypeEnum.ALUNO.getId());

            if (userTypeOpt.isEmpty()) {
                throw new NotFoundException("UserType não encontrado");
            }

            var subscriptionTypeOpt = subscriptionTypeRepository.findByProductKey(paymentProcessDto.getProductKey());

            if (subscriptionTypeOpt.isEmpty()) {
                throw new NotFoundException("SubscriptionType não encontrado");
            }
            user.setSubscriptionType(subscriptionTypeOpt.get());
            userRepository.save(user);

            //Envia e-mail de criação de conta

            mailIntegration.send(user.getEmail(), "Usuário: "+ user.getEmail() + " - Senha: aluno123", "Acesso liberado!");
            return true;

        }

        //Retorna o sucesso ou não do pagamento

        return false;
    }

    private Boolean getSucessPayment(PaymentProcessDto paymentProcessDto, User user) {

        //Cria ou atualiza usuário raspay
        CustomerDto customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.build(user));

        //Cria o pedido de pagamento
        OrderDto orderDto = wsRaspayIntegration.createOrder(OrderMapper.build(customerDto.getId(), paymentProcessDto));

        //Processa o pagamento
        PaymentDto paymentDto = PaymentMapper.build(customerDto.getId(), orderDto.getId(), CreditCardMapper.build(paymentProcessDto.getUserPaymentInfoDto(), user.getCpf()));
        return wsRaspayIntegration.processPayment(paymentDto);
    }
}
