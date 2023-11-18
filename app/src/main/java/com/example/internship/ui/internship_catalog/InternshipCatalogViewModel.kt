package com.example.internship.ui.internship_catalog

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.data.api.InternshipApi
import com.example.internship.models.Internship
import com.example.internship.models.Category
import com.example.internship.models.InternshipFilter
import com.example.internship.models.InternshipFilterData
import com.example.internship.models.InternshipStatus
import com.example.internship.models.InternshipType
import com.example.internship.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternshipCatalogViewModel @Inject constructor(
    private val internshipApi: InternshipApi
):ViewModel() {
    private val _internships = mutableStateListOf<Internship>()
    val internships = mutableStateOf<List<Internship>>(emptyList())
    val isLoading = mutableStateOf(false)
    val shodDialog = mutableStateOf(false)

    //filters
    val durationList = mutableStateListOf(
       InternshipFilterData(
           "менее недели",
           false
       ),
        InternshipFilterData(
            "Менее месяца",
            false
        ),
        InternshipFilterData(
            "Более 3 месяцев",
            false
        )
    )

    val peopleCntList = mutableStateListOf(
        InternshipFilterData(
            "Менее 5",
            false
        ),
        InternshipFilterData(
            "5-15",
            false
        ),
        InternshipFilterData(
            "15-30",
            false
        ),
        InternshipFilterData(
            "30 и более",
            false
        )
    )

    val typeFilterList = mutableStateListOf(
        InternshipFilterData(
            "Проект",
            false
        ),
        InternshipFilterData(
            "Стажировка",
            false
        ),
        InternshipFilterData(
            "Задача",
            false
        )
    )
    val typeList = listOf(
        InternshipFilterData(
            "Проект",
            false
        ),
        InternshipFilterData(
            "Стажировка",
            false
        ),
        InternshipFilterData(
            "Задача",
            false
        )
    )

    val specialityList = mutableStateListOf<InternshipFilterData>().apply {
        Constants.specialities.forEach {
            add(
                InternshipFilterData(
                it,
                false
            ))
        }
    }



    private val filter = mutableStateOf(InternshipFilter())

    val categories = mutableStateOf(
        listOf(
        Category(InternshipStatus.Preparing,true),
        Category(InternshipStatus.InProgress,false),
        Category(InternshipStatus.Archive,false)
        )
    )
    private var currentCategory = categories.value.first()
    fun getInternships(){
        _internships.clear()
        internships.value = emptyList()
        viewModelScope.launch (Dispatchers.IO) {
            isLoading.value = true
            _internships.addAll(internshipApi.fetchInternships())
           //internships.value = _internships.toList()
            getCategory(currentCategory)
            isLoading.value = false
        }
    }

    fun getCategory(selectedCategory: Category){
         internships.value  = _internships.filter {
            it.status.name==selectedCategory.title.name
        }.toList()
        val filtered = mutableListOf<Category>()
        for (category in categories.value){
            category.isSelected = selectedCategory.title.name == category.title.name
            filtered.add(category)
        }
        currentCategory = selectedCategory
        categories.value = filtered
    }

    fun selectDuration(selectedTag:InternshipFilterData){
        val tags = mutableListOf<InternshipFilterData>()
        var isClear = false
        for (category in durationList){
            if (selectedTag.title==category.title&&category.isSelected){
                category.isSelected= false
                filter.value.duration = null
                isClear = true
            }
            else{
                category.isSelected = selectedTag.title == category.title
            }
            tags.add(category)
        }
        durationList.clear()
        durationList.addAll(tags)
        if (!isClear){
            filter.value.duration = selectedTag.title
        }
    }

    fun selectPeopleCnt(selectedTag:InternshipFilterData){
        val tags = mutableListOf<InternshipFilterData>()
        var isClear = false
        for (category in peopleCntList){
            if (selectedTag.title==category.title&&category.isSelected){
                category.isSelected= false
                filter.value.peopleCnt = null
                isClear = true
            }
            else{
                category.isSelected = selectedTag.title == category.title
            }
            tags.add(category)
        }
        peopleCntList.clear()
        peopleCntList.addAll(tags)
        if(!isClear){
            filter.value.peopleCnt = selectedTag.title
        }

    }

    fun selectType(selectedTag:InternshipFilterData){
        val tags = mutableListOf<InternshipFilterData>()
        var isClear = false
        for (category in typeFilterList){
            if (selectedTag.title==category.title&&category.isSelected){
                category.isSelected= false
                filter.value.duration = null
                isClear = true
            }
            else{
                category.isSelected = selectedTag.title == category.title
            }
            tags.add(
                InternshipFilterData(
                title =category.title,
                isSelected = category.isSelected
            )
            )
        }
        typeFilterList.clear()
        typeFilterList.addAll(tags)
        if (!isClear){
            filter.value.type= selectedTag.title
        }

    }

    fun selectSpeciality(selectedTag:InternshipFilterData){
        val tags = mutableListOf<InternshipFilterData>()
        val isClear = false
        for (category in specialityList){
            if (selectedTag.title==category.title&&category.isSelected){
                category.isSelected= false
//                filter.value.duration = null
            }
            else{
                category.isSelected = selectedTag.title == category.title
            }
            tags.add(category)
        }
        specialityList.clear()
        specialityList.addAll(tags)
        if (!isClear){
            filter.value.speciality = selectedTag.title
        }
    }


    fun filterInternships(){
        getCategory(currentCategory)
        filter.value.duration?.let {
            when(it){
                "менее недели"->{
                    internships.value = internships.value.filter { internship->
                        internship.duration<7
                    }
                }
                "Менее месяца"->{
                    internships.value = internships.value.filter {internship->
                        internship.duration<31
                    }

                }
                "Более 3 месяцев"->{
                    internships.value = internships.value.filter { internship->
                        internship.duration>93
                    }
                }
            }
        }
        filter.value.peopleCnt?.let {
            when(it){
                "Менее 5"->{
                    internships.value = internships.value.filter {internship->
                        internship.peopleCnt<5
                    }
                }
                "5-15"->{
                    internships.value = internships.value.filter {internship->
                        internship.peopleCnt in 5..15
                    }

                }
                "15-30"->{
                    internships.value = internships.value.filter {internship->
                            internship.peopleCnt in 15..30
                    }
                }
                "30 и более"->{
                    internships.value = internships.value.filter {internship->
                        internship.peopleCnt>=30
                    }
                }
            }
        }
        filter.value.type?.let { filter->
            internships.value = internships.value.filter {internship->
                filter==internship.name
            }
        }
        filter.value.speciality?.let { filter->
            internships.value = internships.value.filter {internship->
                internship.specialities.find { speciality->
                    speciality==filter
                }==filter
            }
        }
    }
}