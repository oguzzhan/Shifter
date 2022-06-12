package com.ozzy.shifter.data

import javax.inject.Inject

class ShifterRepository @Inject constructor(val dataSource: ShifterDataSource)