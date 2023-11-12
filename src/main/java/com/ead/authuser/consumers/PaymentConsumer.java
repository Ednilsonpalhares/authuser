package com.ead.authuser.consumers;

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
