package logic;

        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import marcelhoerner.codecase.R;

public class CitiesList extends ArrayAdapter<String> {
        private final Activity context;
        private String[] name;
        private String[] temp;
        private String[] type;
        private String[] humi;
        private String[] wind;
        private String[] press;

        public CitiesList(Activity context,
                          String[] name, String[] temp, String[] type, String[] humi, String[] wind, String[] press) {
            super(context, R.layout.list_item_cities, name);
            this.context = context;
            this.name = name;
            this.temp = temp;
            this.type = type;
            this.humi = humi;
            this.wind = wind;
            this.press = press;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_item_cities, null, true);

            TextView txtName = (TextView) rowView.findViewById(R.id.name);
            TextView txtTemp = (TextView) rowView.findViewById(R.id.temp);
            TextView txtType = (TextView) rowView.findViewById(R.id.type);
            TextView txtHumi = (TextView) rowView.findViewById(R.id.humi);
            TextView txtWind = (TextView) rowView.findViewById(R.id.wind);
            TextView txtPress = (TextView) rowView.findViewById(R.id.press);

            txtName.setText(name[position]);
            txtTemp.setText(temp[position]);
            txtType.setText(type[position]);
            txtHumi.setText(humi[position]);
            txtWind.setText(wind[position]);
            txtPress.setText(press[position]);

            return rowView;
        }
}

