package com.clintpauldev.recyclerviewwithheader

data class SessionModel(
    val sessionNumber: Int,
    val sessionStartTime: String,
    val sessionEndTime: String,
    val slotModel: ArrayList<SlotModel>
)