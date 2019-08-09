package `in`.co.logicsoft.webview.room.web

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WebDao {

    @Insert
    suspend fun insert(web: Web)

    @Query("SELECT * FROM web")
    fun getMessage(): LiveData<Web>
}
