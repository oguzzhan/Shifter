package com.ozzy.shifter.viewModel

import com.ozzy.shifter.MainDispatcherRule
import com.ozzy.shifter.data.ShifterRepository
import com.ozzy.shifter.model.Shift
import com.ozzy.shifter.model.ShifterResponse
import com.ozzy.shifter.model.ShifterResult
import com.ozzy.shifter.ui.shiftList.ShiftListViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShiftListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var mockRepository: ShifterRepository

    lateinit var viewModel: ShiftListViewModel

    @Before
    fun before() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = spyk(ShiftListViewModel(mockRepository))
    }

    @Test
    fun `given loading state variable isLoading set true`() {
        coEvery {
            mockRepository.fetchShiftList(any())
        } returns flow {
            emit(ShifterResult.loading())
        }

        runTest {
            viewModel.fetchShiftList()
        }

        coVerify {
            viewModel.isLoading = true
        }
    }

    @Test
    fun `given success state variable shiftList is filled`() {
        val dummyResponse = ShifterResponse(
            listOf(
                Shift(),
                Shift(),
                Shift()
            )
        )

        val shifterResult = ShifterResult.success(dummyResponse)

        coEvery {
            mockRepository.fetchShiftList(any())
        } returns flow {
            emit(shifterResult)
        }

        runTest {
            viewModel.fetchShiftList()
        }

        assert(viewModel.shiftList.size == 3)
    }


    @Test
    fun `given error state variable isLoading set true`() {
        coEvery {
            mockRepository.fetchShiftList(any())
        } returns flow {
            emit(ShifterResult.errorCode(12))
        }

        runTest {
            viewModel.fetchShiftList()
        }

        assert(viewModel.shiftListError?.errorCode?.equals(12) == true)
    }


    @After
    fun tearDown() {
        unmockkAll()
    }
}