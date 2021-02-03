package com.example.aggim.api.response

data class DonateListItemResponse(
        val id: Long, // 이거 없었을땐 ㄱㅊ
        val donationId: Long,
        val userId: Long,
        val donatedVal: Int,
        val donation: DonateResponse
)