package com.example.alexbryksin.delivery.grpc

import bank.venu.mica.PaymentAuthorizationServiceGrpcKt
import com.example.alexbryksin.interceptors.LogGrpcInterceptor
import com.example.alexbryksin.services.PaymentAuthorizationService
import io.mica.serviceprovider.value.v1.ValueProto
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import org.springframework.cloud.sleuth.Tracer
import javax.validation.ConstraintViolationException
import javax.validation.Validator


@GrpcService(interceptors = [LogGrpcInterceptor::class])
class PaymentAuthorizationGrpcService(
    private val paymentAuthorizationService: PaymentAuthorizationService,
    private val tracer: Tracer,
    private val validator: Validator
) : PaymentAuthorizationServiceGrpcKt.PaymentAuthorizationServiceCoroutineImplBase() {

    override suspend fun authorizePayment(request: ValueProto.ObtainValueRequest): ValueProto.ObtainValueResponse {

        return paymentAuthorizationService.authorizePayment(request)
    }

    private fun <T> validate(data: T): T {
        return data.run {
            val errors = validator.validate(data)
            if (errors.isNotEmpty()) throw ConstraintViolationException(errors).also { log.error("validation error: ${it.localizedMessage}") }
            data
        }
    }


    companion object {
        private val log = LoggerFactory.getLogger(PaymentAuthorizationGrpcService::class.java)
        private const val timeOutMillis = 5000L
    }
}

