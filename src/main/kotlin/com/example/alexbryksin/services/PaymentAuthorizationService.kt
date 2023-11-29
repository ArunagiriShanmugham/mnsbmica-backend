package com.example.alexbryksin.services

import io.mica.serviceprovider.value.v1.ValueProto
import io.mica.serviceprovider.value.v1.ValueProto.ObtainValueResponse
import org.springframework.stereotype.Service

@Service
interface PaymentAuthorizationService {
    suspend fun authorizePayment(obtainValueRequest: ValueProto.ObtainValueRequest) : ObtainValueResponse;
}