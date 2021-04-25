package com.bigwalk.test.util

/**
 * Jose Alcérreca (Google, Android)
 *
 * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 *
 * 상기 페이지에서, LiveData 를 이용할 때 하나의 Observer 만 존재하는 경우 SingleLiveData 라는 MutableLiveData 기반
 * 커스텀 클래스가 있는데, 필요시 해당 항목 참조할 것
 *
 * 핸들링 여부에 따라, 컨텐츠나 null 을 리턴하는 이벤트 모델
 * -> 옵저빙 시 중복 핸들링을 방지
 */
open class Event<out T>(private val content: T) {
    /**
     * 이벤트 핸들링 여부
     */
    private var hasBeenHandled = false

    fun peekContent(): T = content

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            return content
        }
    }
}