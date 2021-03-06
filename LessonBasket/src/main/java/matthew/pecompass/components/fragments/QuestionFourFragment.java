package matthew.pecompass.components.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import matthew.pecompass.R;

import static matthew.pecompass.R.id.rg_rq2_1;
import static matthew.pecompass.R.id.rg_rq2_2;
import static matthew.pecompass.R.id.rg_rq2_3;
import static matthew.pecompass.R.id.rg_rq2_4;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFourFragment extends BaseQuestionFragment {

    private RadioGroup rg_rq4_1;
    private RadioGroup rg_rq4_2;
    private RadioGroup rg_rq4_3;
    private RadioGroup rg_rq4_4;
    public QuestionFourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_question_four, container, false);
        rg_rq4_1= (RadioGroup) v.findViewById(R.id.rg_rq4_1);
        rg_rq4_2= (RadioGroup) v.findViewById(R.id.rg_rq4_2);
        rg_rq4_3= (RadioGroup) v.findViewById(R.id.rg_rq4_3);
        rg_rq4_4= (RadioGroup) v.findViewById(R.id.rg_rq4_4);
        return v;
    }

    @Override
    public boolean canPass() {
        if(rg_rq4_1.getCheckedRadioButtonId()!=-1&&rg_rq4_2.getCheckedRadioButtonId()
                !=-1&&rg_rq4_3.getCheckedRadioButtonId()!=-1&&rg_rq4_4.getCheckedRadioButtonId()
                !=-1){
            return true;
        }
        return false;
    }
}
