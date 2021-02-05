package com.example.aggim.common.paging

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

interface LiveDataPagedListBuilder<K, T>{

    fun createDataSource(): DataSource<K, T>

    private fun factory()=object:
        DataSource.Factory<K, T>(){
        override fun create(): DataSource<K, T> = createDataSource()
    }

    private fun config() = PagedList.Config.Builder()
        .setPageSize(5)//어떻게 가져오나 한 페이지를 5개로 처리하도록 설정
        .setEnablePlaceholders(false)
        .build()

    fun buildPagedList() = LivePagedListBuilder(factory(),config()).build()
}