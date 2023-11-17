package com.example.internship.util

import com.example.internship.models.InternPosition

fun internPositionMapper(data:InternPosition):String{
    when(data){
        InternPosition.INTERN->{
            return "Стажер"
        }
        InternPosition.INTERN_SUPPORT->{
            return "Помощник стажера"
        }
        InternPosition.MANAGER->{
            return "Менеджер"
        }
        InternPosition.ADMINISTRATOR->{
            return "Администратор"
        }
        InternPosition.TEAM_MEMBER->{
            return "Учсатник команды"
        }
    }

}