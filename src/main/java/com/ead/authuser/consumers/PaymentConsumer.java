package com.ead.authuser.consumers;

import com.ead.authuser.domain.role.entity.enums.RoleTypeEnum;
import com.ead.authuser.dtos.PaymentEventDto;
import com.ead.authuser.dataprovider.user.entity.PaymentControl;
import com.ead.authuser.dataprovider.user.entity.UserTypeEntityEnum;
import com.ead.authuser.dataprovider.user.entity.UserEntity;
import com.ead.authuser.services.RoleService;
import com.ead.authuser.services.UserService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@Component
public class PaymentConsumer {

   /* @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.paymentEventQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.paymentEvent}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true"))
    )
    public void listenPaymentEvent(@Payload PaymentEventDto paymentEventDto){
        UserEntity userModel = userService.findById(paymentEventDto.getUserId()).get();
        var roleModel = roleService.findByRoleName(RoleTypeEnum.ROLE_STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is Not Found."));

        switch (PaymentControl.valueOf(paymentEventDto.getPaymentControl())){
            case EFFECTED:
                if (userModel.getUserType().equals(UserTypeEntityEnum.USER)) {
                    userModel.setUserType(UserTypeEntityEnum.STUDENT);
                    userModel.getRoles().add(roleModel);
                    userService.updateUser(userModel);
                }
                break;
            case REFUSED:
                if (userModel.getUserType().equals(UserTypeEntityEnum.STUDENT)) {
                    userModel.setUserType(UserTypeEntityEnum.USER);
                    userModel.getRoles().remove(roleModel);
                    userService.updateUser(userModel);
                }
                break;
            case ERROR:
                System.out.println("Payment with ERROR");
        }
    }
*/
}
