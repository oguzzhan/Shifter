package com.ozzy.shifter.data

import com.ozzy.shifter.model.Shift
import com.ozzy.shifter.model.ShifterResponse
import com.ozzy.shifter.model.ShifterResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ShifterRepository @Inject constructor(val dataSource: ShifterDataSource) {
    fun fetchShiftList(filterMap: HashMap<String, String>?): Flow<ShifterResult<ShifterResponse<Shift>>> {
        return object : NetworkBoundRepository<ShifterResponse<Shift>>() {
            override suspend fun fetchFromRemote(): Response<ShifterResponse<Shift>> {
                return dataSource.shiftList(filterMap)
            }
        }.asFlow()
    }
}