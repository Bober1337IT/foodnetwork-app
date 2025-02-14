package com.example.foodnetwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodnetwork.data.Food
import com.example.foodnetwork.data.food
import com.example.foodnetwork.ui.theme.FoodnetworkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodnetworkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FoodnetworkApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FoodnetworkApp(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopBarIcon()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(food) {
                FoodItem(
                    food = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun FoodItem(
    food: Food,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                FoodName(food.name)
                Spacer(Modifier.weight(1f))
                FoodItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            ) {
                FoodIcon(food.imageResourceId)
            }
            if (expanded) {
                FoodDescription(food.description)
            }
        }
    }
}

@Composable
fun FoodName(
    @StringRes foodName: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(
            top = 8.dp,
            start = 8.dp
        )
    ) {
        Text(
            text = stringResource(foodName),
            color = MaterialTheme.colorScheme.tertiary,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}

@Composable
fun FoodItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary
        )
    }

}

@Composable
fun FoodIcon(
    @DrawableRes foodIcon: Int,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(foodIcon),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .aspectRatio(16/9f)
    )
}

@Composable
fun FoodDescription(
    @StringRes foodDescription: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = "Opis:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = stringResource(foodDescription),
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun TopBarIcon(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
){
    Column {
        Spacer(modifier = modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = stringResource(R.string.app_name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    fontFamily = FontFamily.Cursive
                )
            )
        }
        Spacer(modifier = modifier.height(8.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun FoodnetworkPreview() {
    FoodnetworkTheme(darkTheme = true) {
        FoodnetworkApp()
    }
}