package com.route.newsappc38gsat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsappc38gsat.apis.APIManager
import com.route.newsappc38gsat.apis.SourcesItem
import com.route.newsappc38gsat.apis.SourcesResponse
import com.route.newsappc38gsat.ui.theme.NewsAppC38GSatTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    val API_KEY = "c027443ca9624422bfbe9b160b9ec11a"

    /**
     *      UI Architecture Pattern :- MVC - MVVM - MVI
     *
     *      Todo App :-
     *          Data binding
     *          MVVM
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        APIManager.getNewsServices().getNewsSources(API_KEY)
            // Runs on background Thread
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    Log.e("TAG", "${response.body()?.status}")
                    Log.e("TAG", "${response.body()?.sources}")
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }


            })
        setContent {
//                .execute() // Run on Main Thread   X


            NewsAppC38GSatTheme {
                // A surface container using the 'background' color from the theme
                NewsTabsItems(null)
            }
        }
    }
}

@Composable
fun NewsTabsItems(sources: List<SourcesItem>?) {
    var selectedIndex = 0
    if (sources?.isNotEmpty() == true) {
        ScrollableTabRow(selectedTabIndex = selectedIndex, containerColor = Color.Green) {

            sources.forEachIndexed { index, sourcesItem ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    unselectedContentColor = Color.White,
                    selectedContentColor = Color.Green,
                    text = {
                        Text(text = sourcesItem.name ?: "")

                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppC38GSatTheme {
//        NewsTabsItems(sources = listOf())
    }
}