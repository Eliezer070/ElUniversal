package com.eas.sdk.commons.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Notes")
data class Note (val sitio: String = "",
                 @PrimaryKey val id: Int,
                 val main_section: String= "",
                 val id_seccion: Int,
                 val id_subseccion : Int,
                 val seccion: String = "",
                 val title: String = "",
                 val subtitle: String = "",
                 val summary: String = "",
                 val author: String = "",
                 val pubdate: String = "",
                 val pubtime: String = "",
                 val image: String = "",
                 val image_small_size: String = "",
                 val body_html: String = "")