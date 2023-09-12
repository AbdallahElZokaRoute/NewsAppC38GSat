package com.route.newsappc38gsat.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsappc38gsat.R

/**
 *
 * Navigation Component To Navigate between fragments
 *
 * NavController
 * val navController = rememberNavController()
 * navController.navigate("categories")
 *
 * Fragment A to Fragment B
 *
 * class FragmentA : Fragment()
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigationDrawer() {

    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        DrawerHeader()
        DrawerBody()
    }
}

@Composable
fun DrawerHeader() {
    Text(
        text = stringResource(id = R.string.news_app),
        style = TextStyle(color = colorResource(id = R.color.white), fontSize = 22.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(0.7F)
            .fillMaxHeight(0.1F)
            .background(color = colorResource(id = R.color.colorGreen))
            .padding(vertical = 16.dp)
    )
}

@Composable
fun DrawerBody() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.7F)
            .padding(top = 16.dp)
    ) {
        NewsDrawerItem(iconId = R.drawable.ic_categories, titleId = R.string.categories)
        NewsDrawerItem(iconId = R.drawable.ic_settings, titleId = R.string.settings)

    }
}

@Composable
fun NewsDrawerItem(iconId: Int, titleId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Icon(painter = painterResource(id = iconId), contentDescription = "Drawer Icon")
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = titleId),
            style = TextStyle(color = colorResource(id = R.color.colorGrey), fontSize = 18.sp)
        )
    }
}

@Preview(name = "Preview", showSystemUi = false)
@Composable
fun NewsDrawerItemPreview() {
    NewsDrawerItem(iconId = R.drawable.ic_categories, R.string.news_app)
}

@Preview(name = "Preview", showSystemUi = false)
@Composable
fun NewsNavigationDrawerPreview() {
    NewsNavigationDrawer()
}