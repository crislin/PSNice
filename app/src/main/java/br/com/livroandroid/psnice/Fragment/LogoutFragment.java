package br.com.livroandroid.psnice.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import br.com.livroandroid.psnice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends Fragment {

    private View view;
    private LinearLayout btLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_logout, container, false);

        btLogout = view.findViewById(R.id.btLogout);
        btLogout.setOnClickListener(fazerLogout());

        return view;
    }

    private View.OnClickListener fazerLogout() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        };
    }

}
