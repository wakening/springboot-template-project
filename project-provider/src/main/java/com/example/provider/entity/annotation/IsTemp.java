//package com.example.provider.entity.annotation;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.validation.Constraint;
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import javax.validation.Payload;
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
///**
// * 自定义spring校验注解
// *
// * @author wakening
// */
//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.FIELD, ElementType.TYPE_USE})
//@Documented
//@Constraint(validatedBy = IsTemp.Handler.class)
//public @interface IsTemp {
//
//    String message() default "";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//
//    /**
//     * 校验方法的具体实现
//     */
//    @Slf4j
//    class Handler implements ConstraintValidator<IsTemp, String> {
//
//        private IsTemp doesTemp;
//
//        @Override
//        public void initialize(IsTemp constraintAnnotation) {
//            this.doesTemp = constraintAnnotation;
//        }
//
//        @Override
//        public boolean isValid(String strategyId, ConstraintValidatorContext constraintValidatorContext) {
//            log.info(this.doesTemp.message());
//            return true;
//        }
//
//    }
//}
