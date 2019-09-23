package com.jaredsburrows.spoon.example

import android.widget.TextView
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import com.jaredsburrows.spoon.example.MainActivity
import com.squareup.spoon.SpoonRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class AnotherMainActivityTest {
  @get:Rule val spoon = SpoonRule()
  @get:Rule val activityRule = ActivityTestRule(MainActivity::class.java)

  @Test
  @UiThreadTest
  fun testSetText() {
    val act = activityRule.getActivity()
    val text = act.findViewById(android.R.id.text1) as TextView
    spoon.screenshot(act, "startup")

    val steps = 5
    for (i in 1..steps) {
      val step = i.toString()
      act.setText(step)
      spoon.screenshot(act, "step-" + i)
      assertThat(text.getText().toString()).isEqualTo(step)
    }
  }
}
