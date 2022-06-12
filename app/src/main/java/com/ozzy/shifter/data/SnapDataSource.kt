package com.ozzy.shifter.data

import javax.inject.Inject

class ShifterDataSource @Inject constructor(private val service: ShifterService) {
    suspend fun shiftList(filters: HashMap<String, String>?) = service.fetchShiftList(filters)
}