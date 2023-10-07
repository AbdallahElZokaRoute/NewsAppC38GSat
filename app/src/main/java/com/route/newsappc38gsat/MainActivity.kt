package com.route.newsappc38gsat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer

import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsappc38gsat.fragments.categories.CATEGORIES_ROUTE_NAME
import com.route.newsappc38gsat.fragments.categories.CategoriesFragment
import com.route.newsappc38gsat.fragments.news.NEWS_ROUTE_NAME
import com.route.newsappc38gsat.fragments.news.NewsFragment
import com.route.newsappc38gsat.fragments.drawer.NewsNavigationDrawer
import com.route.newsappc38gsat.ui.theme.NewsAppC38GSatTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    /**
     *      Route App -> Jetpack Compose
     *                   Room
     *  MVVM - DataBinding
     *  Chat application (Firebase)
     *
     *  Coroutines + kotlin Flows
     *  Dependency Injection
     *  Unit testing
     *  Integration Testing
     *
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
            val navController = rememberNavController()

            NewsAppC38GSatTheme {
                // A surface container using the 'background' color from the theme
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                ModalNavigationDrawer(
                    drawerContent = {
                        NewsNavigationDrawer()
                    }, drawerState = drawerState
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        topBar = { NewsTopAppBar(drawerState) }
                    ) {
                        val categoryParameterName = "{category_API_id}"
                        NavHost(
                            navController = navController,
                            startDestination = CATEGORIES_ROUTE_NAME,
                            modifier = Modifier.padding(top = it.calculateTopPadding())
                        ) {
                            composable(
                                "$NEWS_ROUTE_NAME/$categoryParameterName", arguments = listOf(
                                    navArgument("category_API_id") {
                                        NavType.StringType
                                    }
                                )
                            ) {
                                val categoryId = it.arguments?.getString("category_API_id")
                                NewsFragment(categoryId)
                            }
                            composable(CATEGORIES_ROUTE_NAME) {
                                CategoriesFragment(navController)
                            }
                        }

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(drawerState: DrawerState) {

    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(
        title = { Text(text = "News App") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_menu),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 12.dp),
                )
            }


        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.colorGreen),
            titleContentColor = colorResource(id = R.color.white),
            navigationIconContentColor = colorResource(id = R.color.white)

        ),
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topEnd = 0.dp,
                    topStart = 0.dp,
                    bottomEnd = 26.dp,
                    bottomStart = 26.dp
                )
            )
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppC38GSatTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ModalNavigationDrawer(
            drawerContent = {
                NewsNavigationDrawer()
            }, drawerState = drawerState, modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                topBar = { },
                modifier = Modifier
                    .fillMaxSize(),
            ) {

//                NewsTabsItems(
//                    listOf(
//                        SourcesItem(name = "ABC "),
//                        SourcesItem(name = "ABC "),
//                        SourcesItem(name = "ABC "),
//                        SourcesItem(name = "ABC "),
//                    ),
//                    modifier = Modifier
//                        .padding(top = paddingValues.calculateTopPadding())
//                        .fillMaxWidth(),
//                )

            }
        }
    }
}
