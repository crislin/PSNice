package br.com.livroandroid.psnice.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.psnice.R;
import br.com.livroandroid.psnice.Service.PSNiceService;
import br.com.livroandroid.psnice.Usuario;

public class EstatisticasFragment extends Fragment {

    private View view;
    private LinearLayout layoutRank;
    private LinearLayout layoutRankExpandido;
    private FrameLayout frameRank;
    private ImageView ivExpandir;
    private boolean rankAberto = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estatisticas, container, false);

        layoutRank = view.findViewById(R.id.layoutRank);
        layoutRankExpandido = view.findViewById(R.id.layoutRankExpandido);
        ivExpandir = view.findViewById(R.id.ivExpandir);
        frameRank = view.findViewById(R.id.frameRank);

        ivExpandir.setOnClickListener(onClickLayoutRank());

        return view;
    }

    private View.OnClickListener onClickLayoutRank() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(frameRank);
                if (!rankAberto){
                    layoutRankExpandido.setVisibility(View.VISIBLE);
                    ivExpandir.setImageDrawable(getResources().getDrawable(R.drawable.ic_comprimir_estatisticas));
                    rankAberto = true;
                } else {
                    layoutRankExpandido.setVisibility(View.GONE);
                    ivExpandir.setImageDrawable(getResources().getDrawable(R.drawable.ic_expandir_estatisticas));
                    rankAberto = false;
                }
            }
        };
    }
}
