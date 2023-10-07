package com.route.newsappc38gsat.fragments.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsappc38gsat.R
import com.route.newsappc38gsat.apis.model.ArticlesItem
import com.route.newsappc38gsat.apis.model.SourcesItem

val NEWS_ROUTE_NAME = "News"

// ViewModel Reference
@Composable
fun NewsFragment(
    categoryId: String?,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    viewModel.getNewsTabsFromAPI(categoryId)
    // Quality OF Code
    Column {
        NewsTabsItems(sources = viewModel.responseState.value)
        // News Lazy Column From API
        NewsList(newsList = viewModel.newsList)
    }
}

@Composable
fun NewsList(newsList: MutableState<List<ArticlesItem>>) {
    LazyColumn {
        items(newsList.value.size) {
            val articleItem = newsList.value.get(it)
            NewsCard(articlesItem = articleItem)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articlesItem: ArticlesItem) {
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
        articlesItem = ArticlesItem(
            "Published At",
            author = "BBC News",
            description = LoremIpsum(35).toString(),
            title = "NEws Article Title"
        ),
    )
}

@Composable
fun NewsTabsItems(
    sources: List<SourcesItem>?,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    if (sources?.isNotEmpty() == true) {
        ScrollableTabRow(selectedTabIndex = selectedIndex, containerColor = Color.Transparent,
            modifier = modifier,
            divider = {},
            indicator = {}) {

            sources.forEachIndexed { index, sourcesItem ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    unselectedContentColor = colorResource(id = R.color.colorGreen),
                    selectedContentColor = Color.White,
                    modifier = if (selectedIndex == index)
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
                if (selectedIndex == index) {
                    viewModel.getNewsBySource(sourcesItem.id ?: "")
                }
            }
        }
    }
}



