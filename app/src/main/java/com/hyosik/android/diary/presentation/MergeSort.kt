package com.hyosik.android.diary.presentation

fun main() {

    val arr = arrayOf(19 , 43 , 11, 3, 31 , 27)

    mergeSort(0 , arr.lastIndex , arr)
    arr.forEach { i ->
        print("$i ")
    }
}

private fun mergeSort(start : Int , last : Int , arr : Array<Int>) {

    if(start < last) {
        val mid = (start+last)/2

        mergeSort(start , mid , arr)
        mergeSort(mid+1 , last , arr)
        merge(start , mid , last , arr)
    }
}


private fun merge(start : Int , mid : Int , last : Int , arr : Array<Int>) {

    var leftStart = start
    var rightStart = mid + 1

    var tempStart = start
    val tempArr = Array<Int>(arr.size) {0}

    while(leftStart <= mid && rightStart <= last) {

        if(arr[leftStart] > arr[rightStart]) {
            tempArr[tempStart++] = arr[rightStart++]
        } else {
            tempArr[tempStart++] = arr[leftStart++]
        }
    }

    while(leftStart <= mid) {
        tempArr[tempStart++] = arr[leftStart++]
    }

    while(rightStart <= mid) {
        tempArr[tempStart++] = arr[rightStart++]
    }


    for(i in start until tempStart) {
        arr[i] = tempArr[i]
    }

}
