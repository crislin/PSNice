package br.com.livroandroid.psnice.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Usuario usuario;
    private PieChart graficoTorta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estatisticas, container, false);

        graficoTorta = view.findViewById(R.id.graficoTorta);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(28.5f, "PS3"));
        entries.add(new PieEntry(57.3f, "PS4"));
        entries.add(new PieEntry(14.2f, "PSVita"));

        PieDataSet set = new PieDataSet(entries, "Consoles");
        set.setColors(view.getResources().getColor(R.color.Green_Emerald),
                view.getResources().getColor(R.color.Yellow_Royal),
                view.getResources().getColor(R.color.Red_Persian));
        PieData data = new PieData(set);
        graficoTorta.setData(data);
        graficoTorta.invalidate();

        return view;
    }
}
