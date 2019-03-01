package cqebd.student.network

/**
 * 基础数据
 * Created by @author xiaofu on 2019/2/27.
 */

class EbdUrl {
    companion object {
        private const val EBD_URL = "https://service-student.cqebd.cn/"
        const val HOMEWORK_END_LOOK = EbdUrl.EBD_URL + "HomeWork/CheckPaper?StudentQuestionsTasksId=%s"
    }
}
