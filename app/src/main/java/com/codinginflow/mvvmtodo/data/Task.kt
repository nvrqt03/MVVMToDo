package com.codinginflow.mvvmtodo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

//type of class that holds data. implements methods we'll use later, like the equals method. this will compare
//two task objects by the data. later when we update the recyclerView, the rv needs to know which items that changed,
//and it does that by comparing them. by using the data class, we get this comparison out of the box
@Entity(tableName = "task_table") // technically don't need to set the tableName, it will use the name of the class
// but a good idea to name our own table
@Parcelize // this needs to be parcelable so we can send this object between different fragments
data class Task(
    val name: String,
    val important: Boolean,
    val completed: Boolean,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
    // we put this in the body because it will be dynamically calculated. it's not something we should pass to a
    // class, nor is it something that's fixed.
    val createdDateFormatted: String
    // we override this get method - whenever this value is accessed, execute this piece of code. this way we
    // can set it not to a fixed value, but to an expression that will be executed dynamically.
    get() = DateFormat.getDateTimeInstance().format(created)
}
