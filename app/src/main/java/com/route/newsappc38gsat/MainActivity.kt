package com.route.newsappc38gsat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer

import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow

import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc38gsat.apis.APIManager
import com.route.newsappc38gsat.apis.SourcesItem
import com.route.newsappc38gsat.apis.SourcesResponse
import com.route.newsappc38gsat.ui.theme.NewsAppC38GSatTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    val API_KEY: String by lazy { "c027443ca9624422bfbe9b160b9ec11a" }

    /**
     *
     *      News App UI
     *
     *
     *
     *
     *
     */
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
//                .execute() // Run on Main Thread   X

            var responseState: List<SourcesItem> by remember {
                mutableStateOf(listOf())

            }

            APIManager.getNewsServices().getNewsSources(API_KEY)
                // Runs on background Thread
                .enqueue(object : Callback<SourcesResponse> {
                    override fun onResponse(
                        call: Call<SourcesResponse>,
                        response: Response<SourcesResponse>
                    ) {
                        Log.e("TAG", "${response.body()?.status}")
                        Log.e("TAG", "${response.body()?.sources}")
                        // Stateful View
                        val body = response.body()
                        responseState = body?.sources ?: listOf()
                    }

                    override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                    }


                })
            NewsAppC38GSatTheme {
                // A surface container using the 'background' color from the theme
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                ModalNavigationDrawer(drawerContent = { }, drawerState = drawerState) {


                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            navigationIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_menu),
                                    contentDescription = " ",
                                    modifier = Modifier
                                        .padding(start = 12.dp)
                                        .clickable {

                                        },
                                )
                            },
                            title = { Text(text = "News App") },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color(0xFF39A552),
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.White

                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        topEnd = 0.dp,
                                        topStart = 0.dp,
                                        bottomEnd = 16.dp,
                                        bottomStart = 16.dp
                                    )
                                )
                        )
                    },

                    ) {

                    NewsTabsItems(responseState, modifier = Modifier.offset(y = 16.dp))
                }


            }
        }
    }
}

@Composable
fun NewsTabsItems(sources: List<SourcesItem>?, modifier: Modifier = Modifier) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    if (sources?.isNotEmpty() == true) {
        ScrollableTabRow(selectedTabIndex = selectedIndex, containerColor = Color.Transparent,
            divider = {},
            indicator = {}) {

            sources.forEachIndexed { index, sourcesItem ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    unselectedContentColor = Color(0xFF39A552),
                    selectedContentColor = Color.White,
                    modifier = if (selectedIndex == index)
                        Modifier
                            .padding(end = 4.dp)
                            .background(Color(0xFF39A552), RoundedCornerShape(50))
                    else
                        Modifier
                            .padding(end = 2.dp)
                            .border(2.dp, Color(0xFF39A552), RoundedCornerShape(50)),
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