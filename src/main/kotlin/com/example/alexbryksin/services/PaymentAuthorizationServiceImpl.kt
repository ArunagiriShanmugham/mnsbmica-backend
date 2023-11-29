package com.example.alexbryksin.services


import io.mica.serviceprovider.value.v1.ValueProto
import io.mica.serviceprovider.value.v1.ValueProto.ObtainValueResponse
import io.mica.serviceprovider.value.v1.obtainValueResponse
import org.springframework.stereotype.Service

@Service
class PaymentAuthorizationServiceImpl : PaymentAuthorizationService {
    override suspend fun authorizePayment(obtainValueRequest: ValueProto.ObtainValueRequest): ValueProto.ObtainValueResponse {

        val response = obtainValueResponse {
            status = ObtainValueResponse.Status.STATUS_FULL_APPROVAL
            approvedAmount = "100"
        }

        return response
    }
}