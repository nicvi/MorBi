package com.example.prediccionmedicaj.ui.clinicHistory;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.StringToPatientEntyty;
import com.example.prediccionmedicaj.objects.GeneralInfo;
import com.example.prediccionmedicaj.objects.Paciente;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import lombok.SneakyThrows;

public class CardCaptionedImageAdapter extends RecyclerView.Adapter<CardCaptionedImageAdapter.ViewHolder>
{
    // <==========|| we are telling the adapter what data it should work with. ||==========> [BEGIN]
    // We’ll use these variables (captions and imageIds) to hold the products data.
    private String[] patientArrayStr;
    // <==========|| we are telling the adapter what data it should work with. ||==========> [END]


    // <==========|| Make the constructor. ||==========> [Begin]

    // We’ll pass the data to the adapter using its constructor.
    public CardCaptionedImageAdapter(
            String[] patientArrayStr
    )
    {
        this.patientArrayStr = patientArrayStr;
    }
    // <==========|| Make the constructor. ||==========> [END]


    // <==========|| Interfaces. ||==========> [BEGIN]
    interface Listener
    {
        void onClick(int positionEntityPatientJsonOnArray);
    }
    // <==========|| Interface. ||==========> [END]


    // <==========|| Add the listeners as a private variable. ||==========> [BEGIN]
    private Listener listener;
    // <==========|| Add the listener as a private variable. ||==========> [END]


    // <==================|| Setting Listeners setters ||==================> [BEGIN]
    public void setListener(Listener listener)
    {
        this.listener = listener;
    }
    // <==================|| Setting Listeners ||==================> [END]


    // <==================|| Define the adapter’s view holder ||==================> [BEGIN}
    // Our recycler view needs to display CardViews, so we specify that our ViewHolder contains
    // CardViews. If you want to display another type of data (views) in the recycler view, you
    // should define it here.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView=v;
        }
    }
    // <==================|| Define the adapter’s view holder ||==================> [END]


    // <==================|| implement the getItemCount() method ||==================> [BEGIN]
    // The length of the captions array equals the number of data items in the recycler view so
    // that number is returned by the getItemCount override method.
    @Override
    public int getItemCount() {
        return patientArrayStr.length;
    }
    // <==================|| implement the getItemCount() method ||==================> [END]


    // <==================|| Override the onCreateViewHolder() method ||==================> [BEGIN]
    // "CaptionedImagesAdapter.ViewHolder" was changed by just "ViewHolder"
    // The method "onCreateViewHolder" gets called when the recycler view needs to create a view
    // holder. (one for each card)
    @NonNull
    @Override
    public CardCaptionedImageAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        // "card_captioned_image" is the LayoutInflator to turn the layout into a CardView.
        // This is nearly identical to code you've already seen in the onCreateView() of fragments.
        CardView cv =
                (CardView) LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card_captioned_image_adapter, parent, false
                );
        return new ViewHolder(cv);
    }
    // <==================|| Override the onCreateViewHolder() method ||==================> [END]


    /*
    <==================|| Add the data to the card views ||==================> [END]
    * The recycler view calls this method (onBindViewHolder) when it wants to use (or reuse) a
      view holder for a new piece of data.
    * This method populate the CardView’s ImageView and TextView with data.
    * The parameter "holder" it's an object of type "ViewHolder", an inner class previously created,
      the inner class ViewHolder method must hold the views that the cardView contain, those are:
        - the cardView per se.
        - a delete product button.
      So the holder is used to refer to those tow previously mentioned views.
     */
    @SneakyThrows
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionFinal = position;
        // The cardView hold the views that shows the information of each product in the collection
        // "Productos"
        CardView cardView = holder.cardView;

        // CardView UI, text views
        try {
            cardViewSetUIViews(cardView, positionFinal);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Recycler view that respond to clicks, for this a listener was added.
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (listener!= null){
                    listener.onClick(positionFinal);
                }
            }
        });
    }
    // <==================|| Add the data to the card views ||==================> [END]


    public void cardViewSetUIViews(CardView cardView, int position) throws JSONException {
        Paciente patient =  new StringToPatientEntyty(patientArrayStr[position]).convertToPatient();

        TextView patientName = cardView.findViewById(R.id.textView_patientName);
        patientName.setText(patient.getGeneralInfo().getNameNoJson());

        // anemia
        TextView textView_patientAnemiaConsulted = cardView.findViewById(R.id.textView_patientAnemiaConsulted);
        switch (patient.getAnemia().getHasAnaemia()){
            case 1:
                textView_patientAnemiaConsulted.setText(R.string.anemiaHighProbability);
                textView_patientAnemiaConsulted.setTextColor(Color.parseColor("#FB7181"));
                break;
            case 0:
                textView_patientAnemiaConsulted.setText(R.string.anemiaLowProbability);
                textView_patientAnemiaConsulted.setTextColor(Color.parseColor("#52870F"));
                break;
            case -1:
                textView_patientAnemiaConsulted.setText(R.string.anemiaConsult);
                textView_patientAnemiaConsulted.setTextColor(Color.parseColor("#9098B1"));
                break;
        }
        // diabetes
        TextView textView_patientDiabetesConsulted = cardView.findViewById(R.id.textView_patientDiabetesConsulted);
        switch (patient.getDiabetes().getHasDiabetes()){
            case 1:
                textView_patientDiabetesConsulted.setText(R.string.diabetesHighProbability);
                textView_patientDiabetesConsulted.setTextColor(Color.parseColor("#FB7181"));
                break;
            case 0:
                textView_patientDiabetesConsulted.setText(R.string.diabetesLowProbability);
                textView_patientDiabetesConsulted.setTextColor(Color.parseColor("#52870F"));
                break;
            case -1:
                textView_patientDiabetesConsulted.setText(R.string.diabetesConsult);
                textView_patientDiabetesConsulted.setTextColor(Color.parseColor("#9098B1"));
                break;
        }
        // Falla cardiaca Consult
        TextView textView_patientDeceaseConsult = cardView.findViewById(R.id.textView_patientDeceaseConsult);
        switch (patient.getFallaCardiaca().getHasDeceased()){
            case 1:
                textView_patientDeceaseConsult.setText(R.string.deceaseHighProbability);
                textView_patientDeceaseConsult.setTextColor(Color.parseColor("#FB7181"));
                break;
            case 0:
                textView_patientDeceaseConsult.setText(R.string.deceaseLowProbability);
                textView_patientDeceaseConsult.setTextColor(Color.parseColor("#52870F"));
                break;
            case -1:
                textView_patientDeceaseConsult.setText(R.string.deceaseConsult);
                textView_patientDeceaseConsult.setTextColor(Color.parseColor("#9098B1"));
                break;
        }
        ImageView userProfilePicClinicHistory = (ImageView)cardView.findViewById(R.id.userProfilePicClinicHistory);
        userProfilePicClinicHistory.setContentDescription(patient.getGeneralInfo().getNameNoJson());
        userProfilePicClinicHistory.setImageResource(cardView.getContext().getResources().getIdentifier( patient.getGeneralInfo().getUserprofilePic() ,"drawable",cardView.getContext().getPackageName()));
    }
}