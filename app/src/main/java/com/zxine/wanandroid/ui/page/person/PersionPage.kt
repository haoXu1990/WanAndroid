package com.zxine.wanandroid.ui.page.person

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zxine.wanandroid.ui.viewmodel.collect.CollectViewModel
import com.zxine.wanandroid.ui.viewmodel.person.PersonViewModel


@Composable
fun PersionPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: PersonViewModel = hiltViewModel()
) {

}