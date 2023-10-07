package com.route.newsappc38gsat.fragments.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsappc38gsat.Constants
import com.route.newsappc38gsat.R
import com.route.newsappc38gsat.apis.model.Category
import com.route.newsappc38gsat.fragments.news.NEWS_ROUTE_NAME

val CATEGORIES_ROUTE_NAME = "categories"

@Composable
fun CategoriesFragment(navHostController: NavHostController) {
    Column {
        Text(
            text = stringResource(id = R.string.pick_your_type_of_interest), style = TextStyle(
                color = colorResource(
                    id = R.color.colorGrey2
                ),
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        CategoriesList(navHostController)

    }
}

@Composable
fun CategoriesList(
    navHostController: NavHostController,
    viewModel: CategoriesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(viewModel.categories.size) {
            // Render single item
            val category = viewModel.categories.get(it)
            CategoryCard(category, it, navHostController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(category: Category, position: Int, navController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = category.backgroundColor)),
        shape = if (position % 2 == 0) RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 0.dp
        ) else RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 16.dp
        ),
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
        onClick = {
            navController.navigate("$NEWS_ROUTE_NAME/${category.apiID}")
        }
    ) {
        Image(
            painter = painterResource(id = category.drawableResId),
            contentDescription = "category Image",
            modifier = Modifier
                .height(80.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillHeight // scaleType : centerCrop - fitXY
        )
        Text(
            text = stringResource(id = category.titleResID), style = TextStyle(
                color = colorResource(
                    id = R.color.white,
                ), fontSize = 18.sp
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}

@Preview(name = "preview", showSystemUi = true)
@Composable
fun CategoriesPreview() {
    CategoriesFragment(rememberNavController())
}