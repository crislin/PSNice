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
import android.widget.ProgressBar;
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
    private LinearLayout layoutPaiEstatisticas;
    private LinearLayout layoutRankExpandido;
    private LinearLayout layoutGamesConsoleExpandido;
    private LinearLayout layoutDetalhesExpandido;
    private ImageView ivExpandirRank;
    private ImageView ivExpandirJogosConsole;
    private ImageView ivExpandirDetalhes;
    private ProgressBar pbGamesConsole;
    private TextView tvEficiencia;
    private TextView tvTotalTrofeus;
    private TextView tvTotalPossivel;
    private TextView tvNumeroRankMundial;
    private TextView tvNumeroRankPais;
    private TextView tvPs4Porcentagem;
    private TextView tvPs4Jogos;
    private TextView tvPs3Porcentagem;
    private TextView tvPs3Jogos;
    private TextView tvPsVitaPorcentagem;
    private TextView tvPsVitaJogos;

    private boolean rankAberto = false;
    private boolean gamesConsoleAberto = false;
    private boolean detalhesAberto = false;
    private int totalTrofeus;
    private int rankMundial;
    private int rankPais;
    private float eficiencia;
    private int totalTrofeusPossiveis;
    private int ps3Games;
    private int ps4Games;
    private int psVitaGames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estatisticas, container, false);

        layoutRank = view.findViewById(R.id.layoutRank);
        layoutRankExpandido = view.findViewById(R.id.layoutRankExpandido);
        layoutGamesConsoleExpandido = view.findViewById(R.id.layoutGamesConsoleExpandido);
        layoutDetalhesExpandido = view.findViewById(R.id.layoutDetalhesExpandido);
        ivExpandirRank = view.findViewById(R.id.ivExpandirRank);
        ivExpandirJogosConsole = view.findViewById(R.id.ivExpandirJogosConsole);
        ivExpandirDetalhes = view.findViewById(R.id.ivExpandirDetalhes);
        pbGamesConsole = view.findViewById(R.id.pbGamesConsole);
        layoutPaiEstatisticas = view.findViewById(R.id.layoutPaiEstatisticas);
        tvEficiencia = view.findViewById(R.id.tvEficiencia);
        tvTotalTrofeus = view.findViewById(R.id.tvTotalTrofeus);
        tvTotalPossivel = view.findViewById(R.id.tvTotalPossivel);
        tvNumeroRankMundial = view.findViewById(R.id.tvNumeroRankMundial);
        tvNumeroRankPais = view.findViewById(R.id.tvNumeroRankPais);
        tvPs4Porcentagem = view.findViewById(R.id.tvPs4Porcentagem);
        tvPs4Jogos = view.findViewById(R.id.tvPs4Jogos);
        tvPs3Porcentagem = view.findViewById(R.id.tvPs3Porcentagem);
        tvPs3Jogos = view.findViewById(R.id.tvPs3Jogos);
        tvPsVitaPorcentagem = view.findViewById(R.id.tvPsVitaPorcentagem);
        tvPsVitaJogos = view.findViewById(R.id.tvPsVitaJogos);

        ivExpandirRank.setOnClickListener(onClickLayoutRank());
        ivExpandirJogosConsole.setOnClickListener(onClickLayoutGamesConsole());
        ivExpandirDetalhes.setOnClickListener(onClickLayoutDetalhes());

        totalTrofeus = getArguments().getInt("totalTrofeus");
        rankMundial = getArguments().getInt("rankMundial");
        rankPais = getArguments().getInt("rankPais");
        eficiencia = getArguments().getFloat("eficiencia");
        totalTrofeusPossiveis = getArguments().getInt("totalTrofeusPossiveis");
//        ps3Games = getArguments().getInt("ps3Games");
//        ps4Games = getArguments().getInt("ps4Games");
//        psVitaGames = getArguments().getInt("psVitaGames");

        carregaDetalhes();

        return view;
    }

    private View.OnClickListener onClickLayoutDetalhes() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(layoutPaiEstatisticas);
                if (!detalhesAberto){
                    layoutDetalhesExpandido.setVisibility(View.VISIBLE);
                    ivExpandirDetalhes.setImageDrawable(getResources().getDrawable(R.drawable.ic_comprimir_estatisticas));
                    detalhesAberto = true;
                } else {
                    layoutDetalhesExpandido.setVisibility(View.GONE);
                    ivExpandirDetalhes.setImageDrawable(getResources().getDrawable(R.drawable.ic_expandir_estatisticas));
                    detalhesAberto = false;
                }
            }
        };
    }

    private View.OnClickListener onClickLayoutRank() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(layoutPaiEstatisticas);
                if (!rankAberto){
                    layoutRankExpandido.setVisibility(View.VISIBLE);
                    ivExpandirRank.setImageDrawable(getResources().getDrawable(R.drawable.ic_comprimir_estatisticas));
                    rankAberto = true;
                } else {
                    layoutRankExpandido.setVisibility(View.GONE);
                    ivExpandirRank.setImageDrawable(getResources().getDrawable(R.drawable.ic_expandir_estatisticas));
                    rankAberto = false;
                }
            }
        };
    }

    private View.OnClickListener onClickLayoutGamesConsole() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(layoutPaiEstatisticas);
                if (!gamesConsoleAberto){
                    layoutGamesConsoleExpandido.setVisibility(View.VISIBLE);
                    ivExpandirJogosConsole.setImageDrawable(getResources().getDrawable(R.drawable.ic_comprimir_estatisticas));
                    gamesConsoleAberto = true;
                } else {
                    layoutGamesConsoleExpandido.setVisibility(View.GONE);
                    ivExpandirJogosConsole.setImageDrawable(getResources().getDrawable(R.drawable.ic_expandir_estatisticas));
                    gamesConsoleAberto = false;
                }
            }
        };
    }

    private void carregaDetalhes(){
        tvTotalTrofeus.setText(String.valueOf(totalTrofeus));
        tvEficiencia.setText(String.valueOf(eficiencia).replace(".", ","));
        tvTotalPossivel.setText(String.valueOf(totalTrofeusPossiveis));
        tvNumeroRankMundial.setText(String.valueOf(rankMundial));
        tvNumeroRankPais.setText(String.valueOf(rankPais));
//        tvPs3Jogos.setText(String.valueOf(ps3Games));
//        tvPs4Jogos.setText(String.valueOf(ps4Games));
//        tvPsVitaJogos.setText(String.valueOf(psVitaGames));
//        calculaJogos();
    }

//    private void calculaJogos(){
//        int totalJogos = ps3Games + ps4Games + psVitaGames;
//        int umPorCento = totalJogos / 100;
//        tvPs4Porcentagem.setText(String.valueOf(ps4Games * umPorCento));
//        tvPs3Porcentagem.setText(String.valueOf(ps3Games * umPorCento));
//        tvPsVitaPorcentagem.setText(String.valueOf(psVitaGames * umPorCento));
//        pbGamesConsole.setProgress(ps4Games * umPorCento);
//        pbGamesConsole.setSecondaryProgress((ps4Games * umPorCento) + (ps3Games * umPorCento));
//    }
}
