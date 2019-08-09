package `in`.co.logicsoft.webview.room.web

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "web")
data class Web(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String
)