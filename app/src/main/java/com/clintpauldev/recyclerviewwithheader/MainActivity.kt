package com.clintpauldev.recyclerviewwithheader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.clintpauldev.recyclerviewwithheader.Constants.SESSION_END_TIME_1
import com.clintpauldev.recyclerviewwithheader.Constants.SESSION_END_TIME_2
import com.clintpauldev.recyclerviewwithheader.Constants.SESSION_NUMBER_1
import com.clintpauldev.recyclerviewwithheader.Constants.SESSION_NUMBER_2
import com.clintpauldev.recyclerviewwithheader.Constants.SESSION_START_TIME_1
import com.clintpauldev.recyclerviewwithheader.Constants.SESSION_START_TIME_2
import com.clintpauldev.recyclerviewwithheader.databinding.ActivityMainBinding
import com.google.gson.Gson
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter

class MainActivity : AppCompatActivity(), SlotSection.ClickListener {
    private var sectionedAdapter: SectionedRecyclerViewAdapter? = null
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupDummyData()
    }

    private fun setupDummyData() {
        val sessionModelArrayList: ArrayList<SessionModel> = ArrayList()

        //        Session1 details
        var slotModel1: SlotModel?
        val slotModelArrayList1: ArrayList<SlotModel> = ArrayList()
        for (i in 1..25) {
            slotModel1 = SlotModel(slotToken = i.toString())
            slotModelArrayList1.add(slotModel1)
        }
        val sessionModel1 = SessionModel(
            sessionNumber = SESSION_NUMBER_1,
            sessionStartTime = SESSION_START_TIME_1,
            sessionEndTime = SESSION_END_TIME_1,
            slotModel = slotModelArrayList1
        )


        //        Session2 details
        var slotModel2: SlotModel?
        val slotModelArrayList2: ArrayList<SlotModel> = ArrayList()
        for (i in 25..50) {
            slotModel2 = SlotModel(slotToken = i.toString())
            slotModelArrayList2.add(slotModel2)
        }
        val sessionModel2 = SessionModel(
            sessionNumber = SESSION_NUMBER_2,
            sessionStartTime = SESSION_START_TIME_2,
            sessionEndTime = SESSION_END_TIME_2,
            slotModel = slotModelArrayList2
        )

        sessionModelArrayList.add(sessionModel1)
        sessionModelArrayList.add(sessionModel2)

        setupAdapter(sessionModelArrayList)
    }

    private fun setupAdapter(sessionModelArrayList: java.util.ArrayList<SessionModel>) {


        sectionedAdapter = SectionedRecyclerViewAdapter()
        val loadSlotsUseCase = LoadSlotsUseCase()

        for (i in 0 until sessionModelArrayList.size) {
            sectionedAdapter!!.addSection(
                SlotSection(
                    sessionModelArrayList[i].sessionStartTime + " " + sessionModelArrayList[i]
                        .sessionEndTime,
                    sessionModelArrayList[i].sessionNumber,
                    loadSlotsUseCase.execute(
                        sessionModelArrayList[i].slotModel
                    ),
                    this,
                    this
                )
            )
        }


//        final RecyclerView recyclerView = findViewById(R.id.recyclerview);


//        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        val glm = GridLayoutManager(this, 3)
        glm.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (sectionedAdapter!!.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    3
                } else 1
            }
        }
        binding?.recyclerView?.layoutManager = glm
        binding?.recyclerView?.adapter = sectionedAdapter

    }

    override fun onItemRootViewClicked(
        slotModel: SlotModel?,
        sessionNumber: Int
    ) {
        Log.e(
            "slotModel",
            "slotDetails :" + " " + Gson().toJson(slotModel) + " " + "sessionNumber :" + " " + sessionNumber
        )

    }
}