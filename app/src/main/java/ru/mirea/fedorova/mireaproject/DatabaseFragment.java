package ru.mirea.fedorova.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

import static ru.mirea.fedorova.mireaproject.RecyclerViewAdapterData.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatabaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatabaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DatabaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatabaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatabaseFragment newInstance(String param1, String param2) {
        DatabaseFragment fragment = new DatabaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_database, container, false);
    }

    @Entity(tableName = "ProgrammingLanguages")
    public static class Language {
        @PrimaryKey(autoGenerate = true)
        private  Long id;
        private  String name;
        private  int year;
        private  String description;

        public Language(Long id, String name, int year, String description) {
            this.id = id;
            this.name = name;
            this.year = year;
            this.description = description;
        }

        public Long getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public int getYear() {
            return this.year;
        }

        public String getDescription() {
            return this.description;
        }
    }

    @Dao
    public interface LanguageDao {

        @Query("SELECT * FROM ProgrammingLanguages")
        List<Language> getAll();

        @Insert (onConflict = OnConflictStrategy.REPLACE)
        void insert(Language language);

        @Update
        void update(Language language);

        @Delete
        void delete(Language language);
    }

    @Database(entities = {Language.class}, version = 1)
    public abstract static class LanguageDatabase extends RoomDatabase {
        public abstract LanguageDao getLanguageDao();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LanguageDatabase database = Room.databaseBuilder(getContext(), LanguageDatabase.class,
                "ProgrammingLanguages").allowMainThreadQueries().build();
        LanguageDao dao = database.getLanguageDao();

        /*Language language = new Language(null, "Python", 1991,
                "Высокоуровневый язык программирования общего назначения с " +
                        "динамической строгой типизацией и автоматическим управлением памятью, " +
                        "ориентированный на повышение производительности разработчика, " +
                        "читаемости кода и его качества, а также на обеспечение переносимости " +
                        "написанных на нём программ.");
        dao.insert(language);
        language = new Language(null, "Java", 1995,
                "Строго типизированный объектно-ориентированный язык программирования " +
                        "общего назначения, разработанный компанией Sun Microsystems (в " +
                        "последующем приобретённой компанией Oracle).");
        dao.insert(language);
        language = new Language(null, "JavaScript ", 1995,
                "Мультипарадигменный язык программирования. Поддерживает объектно-" +
                        "ориентированный, императивный и функциональный стили. Самая первая " +
                        "реализация JavaScript была создана Бренданом Эйхом в компании Netscape.");
        dao.insert(language);
        language = new Language(null, "C#", 2000, "Объектно- и " +
                "компонентно-ориентированный язык программирования. Разработан в 1998—2001 годах " +
                "группой инженеров компании Microsoft под руководством Андерса Хейлсберга и " +
                "Скотта Вильтаумота как язык разработки приложений для платформы Microsoft .NET " +
                "Framework.");
        dao.insert(language);
        language = new Language(null, "Kotlin", 2011,
                "Статически типизированный, объектно-ориентированный язык " +
                        "программирования, работающий поверх Java Virtual Machine и " +
                        "разрабатываемый компанией JetBrains. Язык назван в честь острова Котлин " +
                        "в Финском заливе, на котором расположен город Кронштадт.");
        dao.insert(language);
        language = new Language(null, "Swift", 2014,
                "Открытый мультипарадигмальный компилируемый язык программирования " +
                        "общего назначения. Создан компанией Apple в первую очередь для " +
                        "разработчиков iOS и macOS.");
        dao.insert(language);*/

        List<Language> listLanguages = dao.getAll();

        RecyclerView recyclerViewData = view.findViewById(R.id.recyclerViewData);
        recyclerViewData.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerViewAdapterData.OnLanguageClickListener languageClickListener =
                new RecyclerViewAdapterData.OnLanguageClickListener() {
            @Override
            public void onLanguageClick(Language language, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(language.getName()).setMessage(language.getDescription())
                        .setPositiveButton("OK", null).create();
                builder.show();
            };
        };

        RecyclerViewAdapterData adapterData = new RecyclerViewAdapterData(getActivity(),
                listLanguages, languageClickListener);
        recyclerViewData.setAdapter(adapterData);
    }
}