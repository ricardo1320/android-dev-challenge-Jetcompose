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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

private const val KEY_ARG_DETAILS_PUPPY_NAME = "KEY_ARG_DETAILS_PUPPY_NAME"

fun launchDetailsActivity(context: Context, item: Puppy) {
    context.startActivity(createDetailsActivityIntent(context, item))
}

fun createDetailsActivityIntent(context: Context, item: Puppy): Intent {
    val intent = Intent(context, DetailsActivity::class.java)
    intent.putExtra(KEY_ARG_DETAILS_PUPPY_NAME, item)
    return intent
}

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val puppyClicked = getDetailsArgs(intent)

        setContent {
            MyTheme {
                Details(puppyClicked)
            }
        }
    }

    private fun getDetailsArgs(intent: Intent): Puppy {
        return intent.getParcelableExtra(KEY_ARG_DETAILS_PUPPY_NAME)!!
    }
}

// Start building your app here!
@Composable
fun Details(puppy: Puppy) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(text = "Puppy details")
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_black_48dp),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = {})
                            .padding(8.dp)
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_email_black_48dp),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = {}) // TODO: Show not implemented dialog.
                            .padding(all = 8.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_share_black_48dp),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = {}) // TODO: Show not implemented dialog.
                            .padding(all = 8.dp)
                    )
                },
                elevation = 0.dp
            )
            PuppyDetails(puppy)
        }
    }
}

@Composable
fun PuppyDetails(puppy: Puppy) {
    Card(
        shape = RoundedCornerShape(32.dp),
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(1.dp, Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Column() {
            Image(
                painterResource(id = puppy.imageUrl), "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = puppy.name,
                modifier = Modifier.padding(all = 8.dp),
                style = MaterialTheme.typography.h4
            )
            Text(
                text = "${puppy.size}   •   ${puppy.weight}   •   ${puppy.ageGroup}",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(all = 8.dp)
            )
            Text(
                text = "${puppy.color}   •   ${puppy.gender}",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(all = 8.dp)
            )
            Text(
                text = "Hi I am a ${puppy.breed}. ${puppy.description}",
                modifier = Modifier
                    .padding(all = 24.dp),
                maxLines = 10
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun Preview() {
    MyTheme {
        val puppy = Puppy("Carlos", "Pug", "2.5kg", "Adult", "Small", "Black", "Male", "please adopt me!", R.drawable.puppy1)
        Details(puppy)
    }
}
