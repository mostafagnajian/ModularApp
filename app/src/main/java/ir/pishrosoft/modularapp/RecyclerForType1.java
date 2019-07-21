package ir.pishrosoft.modularapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.pishrosoft.modularapp.adapters.RecyclerAdapterType1;
import ir.pishrosoft.modularapp.models.ModelMainItems;
import ir.pishrosoft.modularapp.models.Type1Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerForType1 extends Fragment {
    private Unbinder unbinder;
    public static NavController navController;
    RecyclerView mRecycler;
    RecyclerAdapterType1 mAdapter;
    List<Type1Data> mItem = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        String url = getArguments().getString("url");


        GetDataService getDataService = RetrofitClientInstanceHTML.getApiService(getContext());
        Call<Type1Data> call = getDataService.getType1Json(url);
        call.enqueue(new Callback<Type1Data>() {
            @Override
            public void onResponse(Call<Type1Data> call, Response<Type1Data> response) {
//                mItem = response.body().getButtons();

//                generateDAtaList();
                Toast.makeText(getContext(), "Secsessssss", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Type1Data> call, Throwable t) {
                Toast.makeText(getContext(), "Faillllll", Toast.LENGTH_LONG).show();

            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        titleMain.setText(getArguments().getString("title"));



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { navController.navigate(R.id.action_recyclerForType12_to_mainFragment); }});

        mRecycler = mRecyclerView;
        mRecycler.setHasFixedSize(true);
        mAdapter = new RecyclerAdapterType1(mItem, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mAdapter);



    }
    public interface YourEndpoints {

    }

    @BindView(R.id.titleMain)
    TextView titleMain;
    @BindView(R.id.backBtnAn)
    TextView backBtn;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
}
