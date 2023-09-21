package net.quazar.logisticsassistant

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import net.quazar.logisticsassistant.data.model.Task
import net.quazar.logisticsassistant.data.model.TaskDescription
import net.quazar.logisticsassistant.placeholder.PlaceholderContent
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * A fragment representing a list of Items.
 */
class TasksFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val list = ArrayList<Task>()
                list.add(
                    Task(
                        1,
                        false,
                        TaskDescription(
                            "Мебель",
                            "Москва",
                            LocalDate.now(),
                            LocalTime.now(),
                            "Склад 51, Ул. Пушника 124Б",
                            "Склад 38, Ул. Розенбаума 89",
                            "Тентованный",
                            "Прописанные детали заказа",
                            "Прописанные параметры по оплате",
                            "+7 800 896 52 63",
                            "Иванов Владимир Иосифович"
                        )
                    )
                )
                list.add(
                    Task(
                        2,
                        true,
                        TaskDescription(
                            "Мебель",
                            "Москва",
                            LocalDate.now(),
                            LocalTime.now(),
                            "Склад 41, Ул. Тверская 12Б",
                            "Склад 33, Ул. Пушкина 8",
                            "Тентованный",
                            "Прописанные детали заказа",
                            "Прописанные параметры по оплате",
                            "+7 800 896 52 63",
                            "Иванов Владимир Иосифович"
                        )
                    )
                )
                list.add(
                    Task(
                        3,
                        false,
                        TaskDescription(
                            "Мебель",
                            "Москва",
                            LocalDate.now(),
                            LocalTime.now(),
                            "Склад 41, Ул. Тверская 12Б",
                            "Склад 33, Ул. Пушкина 8",
                            "Тентованный",
                            "Прописанные детали заказа",
                            "Прописанные параметры по оплате",
                            "+7 800 896 52 63",
                            "Иванов Владимир Иосифович"
                        )
                    )
                )
                adapter = MyTasksRecyclerViewAdapter(list)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TasksFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}