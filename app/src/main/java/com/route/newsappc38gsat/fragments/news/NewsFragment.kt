package com.route.newsappc38gsat.fragments.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.entity.ArticlesItemDTO
import com.example.domain.entity.SourcesItemDTO
import com.route.newsappc38gsat.R
import kotlinx.coroutines.launch

val NEWS_ROUTE_NAME = "News"

// ViewModel Reference
@Composable
fun Render(categoryId: String?, viewModel: NewsViewModel = hiltViewModel()) {
    val state = viewModel.states.value
    when (state) {
        is NewsViewStates.Idle -> {
            LaunchedEffect(key1 = true) {
                viewModel.eventsChannel.send(NewsViewEvents.EnteredNewsScreen(categoryId ?: ""))
            }
        }

        is NewsViewStates.Loading -> {
            LoadingCircularProgressIndicator()
        }

        is NewsViewStates.Error -> {
            ErrorDialog(errorMessage = state.errorMessage)
        }

        is NewsViewStates.LoadedSources -> {
            Column {
                NewsTabsItems(sources = state.sources)
                // News Lazy Column From API
                NewsList(newsList = state.newsList ?: listOf())
            }
        }
    }
}

@Composable
fun ErrorDialog(viewModel: NewsViewModel = hiltViewModel(), errorMessage: String) {
    val coroutineScope = rememberCoroutineScope()
    AlertDialog(onDismissRequest = {}, confirmButton = {
        Button(onClick = {
            coroutineScope.launch {
                viewModel.eventsChannel.send(NewsViewEvents.Idle)
            }
        }) {
            Text(text = "OK")
        }
    }, title = {
        Text(text = errorMessage)

    })
}

@Composable
fun LoadingCircularProgressIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = colorResource(id = R.color.colorGreen))
    }
}


@Composable
fun NewsFragment(
    categoryId: String?,
) {

    // Quality OF Code
    Render(categoryId = categoryId)
}

@Composable
fun NewsList(newsList: List<ArticlesItemDTO>) {
    LazyColumn {
        items(newsList.size) {
            val articleItem = newsList.get(it)
            NewsCard(articlesItem = articleItem)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articlesItem: ArticlesItemDTO) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 14.dp)
    ) {
        // Url of the image
        //Image(painter =, contentDescription =)
        GlideImage(
            model = articlesItem.urlToImage,
            contentDescription = "News Image",
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
        //Author
        Text(
            text = articlesItem.author ?: "",
            style = TextStyle(colorResource(id = R.color.colorGrey3))
        )
        Text(
            text = articlesItem.title ?: "",
            style = TextStyle(colorResource(id = R.color.colorGrey3))
        )
        Text(
            text = articlesItem.publishedAt ?: "",

            style = TextStyle(colorResource(id = R.color.colorGrey3)),
            modifier = Modifier.align(Alignment.End)
        )

    }
}

@Preview(name = "Preview", showSystemUi = true)
@Composable
fun NewsPreview() {
    NewsCard(
        articlesItem = ArticlesItemDTO(
            publishedAt = "Published At",
            author = "BBC News",
            description = LoremIpsum(35).toString(),
            title = "NEws Article Title"
        ),
    )
}

@Composable
fun NewsTabsItems(
    sources: List<SourcesItemDTO>?,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    if (sources?.isNotEmpty() == true) {
        ScrollableTabRow(selectedTabIndex = viewModel.selectedIndex.intValue,
            containerColor = Color.Transparent,
            modifier = modifier,
            divider = {},
            indicator = {}) {

            sources.forEachIndexed { index, sourcesItem ->
                Tab(
                    selected = viewModel.selectedIndex.intValue == index,
                    onClick = {
                        viewModel.selectedIndex.intValue = index
                        coroutineScope.launch {
                            viewModel.eventsChannel.send(NewsViewEvents.SelectTabEvent(sourcesItem.id))
                        }
                    },
                    unselectedContentColor = colorResource(id = R.color.colorGreen),
                    selectedContentColor = Color.White,
                    modifier = if (viewModel.selectedIndex.intValue == index)
                        Modifier
                            .padding(end = 4.dp)
                            .background(
                                colorResource(id = R.color.colorGreen),
                                RoundedCornerShape(50)
                            )
                    else
                        Modifier
                            .padding(end = 2.dp)
                            .border(
                                2.dp,
                                colorResource(id = R.color.colorGreen),
                                RoundedCornerShape(50)
                            ),
                    text = {
                        Text(text = sourcesItem.name ?: "")

                    })

            }
        }
    }
}

