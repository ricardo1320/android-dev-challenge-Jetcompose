/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

typealias OnItemClicked = (Puppy) -> Unit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(
                    fillPuppyList(),
                    onItemClicked = { launchDetailsActivity(context = this, item = it) }
                )
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(puppies: List<Puppy>, onItemClicked: OnItemClicked) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primarySurface,
                title = {
                    Text(text = "Puppy adoption")
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_menu_black_48dp),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = {})
                            .padding(8.dp)
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_black_48dp),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = {}) // TODO: Show not implemented dialog.
                            .padding(all = 8.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_pets_black_48dp),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = {}) // TODO: Show not implemented dialog.
                            .padding(all = 8.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings_black_48dp),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = {}) // TODO: Show not implemented dialog.
                            .padding(all = 8.dp)
                    )
                },
                elevation = 0.dp
            )
            PuppyList(puppies, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun PuppyList(puppies: List<Puppy>, onItemClicked: OnItemClicked) {
    LazyColumn {
        items(puppies) { puppy ->
            Divider(color = Color.White, modifier = Modifier.height(8.dp))
            PuppyCard(puppy, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun PuppyCard(puppy: Puppy, onItemClicked: OnItemClicked) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onItemClicked(puppy) }
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Surface(
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(20),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(painterResource(id = puppy.imageUrl), "", contentScale = ContentScale.Crop)
        }
        Column(
            modifier = Modifier
                .padding(start = 56.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = puppy.name, style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = puppy.breed, style = MaterialTheme.typography.body2)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(puppies = fillPuppyList(), onItemClicked = { /*TODO*/ })
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(puppies = fillPuppyList(), onItemClicked = { /*TODO*/ })
    }
}

fun fillPuppyList(): List<Puppy> {
    return listOf(
        Puppy("Carlos", "Pug", "2.5kg", "Adult", "Small", "Black", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy1),
        Puppy("Luka", "Beagle", "3.9kg", "Young", "Middle", "Brown/White", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy2),
        Puppy("Polkas", "Husky", "4.4kg", "Young", "Big", "White", "Female", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy3),
        Puppy("Sam", "Unknown", "1.1kg", "Adult", "Mini", "Grey/White", "Female", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy4),
        Puppy("Scott", "Corgi", "2.34kg", "Young", "Middle", "Brown/Black/White", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy5),
        Puppy("Alex", "Border Collie", "7.8kg", "Puppy", "Big", "Black/White", "Female", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy6),
        Puppy("Martyna", "Chow-Chow", "15.5kg", "Old", "Big", "Brown", "Female", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy7),
        Puppy("Adan", "West Highland Terrier", "2.3kg", "Adult", "Mini", "White", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy8),
        Puppy("David", "Dalmatian", "11.19kg", "Young", "Big", "Black/White", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy9),
        Puppy("Oscar", "Unknown", "6.2kg", "Old", "Small", "Brown/Black/White", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy10),
        Puppy("Yoda", "Chihuahua", "2.4kg", "Adult", "Small", "Brown", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy11),
        Puppy("Müller", "German Shepherd", "18kg", "Old", "Jumbo", "Brown/Black", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy12),
        Puppy("Choco", "Labrador", "14.63kg", "Teenager", "Big", "Brown", "Female", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy13),
        Puppy("Lola", "Schnauzer", "1.31kg", "Puppy", "Mini", "Black/Grey/White", "Female", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy14),
        Puppy("Josh", "Poodle", "2.47kg", "Puppy", "Mini", "Brown", "Male", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy15),
        Puppy("Sara", "Golden Retriever", "15.8kg", "Teenager", "Big", "Brown", "Female", "Please adopt me! We can be best friends, you won't regret it. Vaccinations up to date.", R.drawable.puppy16)
    )
}
