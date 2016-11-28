package matthew.pecompass.components.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import matthew.pecompass.R;

import static matthew.pecompass.R.id.rg_rq2_1;
import static matthew.pecompass.R.id.rg_rq2_2;
import static matthew.pecompass.R.id.rg_rq2_3;
import static matthew.pecompass.R.id.rg_rq2_4;
import static matthew.pecompass.R.id.rg_rq4_1;
import static matthew.pecompass.R.id.rg_rq4_2;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFiveFragment extends BaseQuestionFragment {

    private RadioGroup rg_rq5_1;
    private RadioGroup rg_rq5_2;
    private EditText et_comment;
    public QuestionFiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_question_five, container, false);
        rg_rq5_1= (RadioGroup) v.findViewById(rg_rq4_1);
        rg_rq5_2= (RadioGroup) v.findViewById(rg_rq4_2);
        et_comment= (EditText) v.findViewById(R.id.et_comment);
        return v;
    }

    @Override
    public boolean canPass() {
        if(rg_rq5_1.getCheckedRadioButtonId()!=-1&&rg_rq5_2.getCheckedRadioButtonId()
                !=-1){
            return true;
        }
        return false;
    }
}
