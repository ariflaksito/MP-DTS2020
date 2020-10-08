package net.ariflaksito.app07c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class CountryAdapter extends BaseAdapter {

    Context context;
    String[] country;
    String[] capital;
    int[] population;
    LayoutInflater inflater;

    public CountryAdapter(Context context, String[] country, String[] capital,
                       int[] population) {
        this.context = context;
        this.country = country;
        this.capital = capital;
        this.population = population;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return country.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_countries, null);
        TextView tvCountry = (TextView) convertView.findViewById(R.id.tv_country);
        TextView tvCapital = (TextView) convertView.findViewById(R.id.tv_capital);
        TextView tvPopulation = (TextView) convertView.findViewById(R.id.tv_population);

        tvCountry.setText(country[position]);
        tvCapital.setText(capital[position]);

        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        tvPopulation.setText(nf.format(population[position])+"");

        return convertView;
    }
}
