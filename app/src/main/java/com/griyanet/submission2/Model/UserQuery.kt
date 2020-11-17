package com.griyanet.submission2.Model

data class UserQuery(
        val incomplete_results: Boolean,
        val items: List<Item>,
        val total_count: Int
)