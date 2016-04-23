package com.aabcdata.sound;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.apptracker.android.track.AppTracker;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import io.presage.Presage;
import io.presage.utils.IADHandler;

import static android.content.Intent.ACTION_VIEW;
//import android.support.v7.widget.ShareActionProvider;
// Leadbolt SDK imports

 public class FartSound extends Activity implements OnInitListener {

    public  MediaPlayer mediaPlayer;
     private ShareActionProvider mShareActionProvider;
    //Definición del menú en menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        /** Inflating the current activity's menu with res/menu/items.xml */
        getMenuInflater().inflate(R.menu.menu, menu);

        /** Getting the actionprovider associated with the menu item whose id is share */
        mShareActionProvider = (ShareActionProvider) menu.findItem(R.id.menu_share).getActionProvider();

        /** Setting a share intent */
        mShareActionProvider.setShareIntent(getDefaultShareIntent());

        return super.onCreateOptionsMenu(menu);
    }

     /** Returns a share intent */
     private Intent getDefaultShareIntent(){
         Intent intent = new Intent(Intent.ACTION_SEND);
         intent.setType("text/plain");
         intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject1));
         intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.extra_text1));
         return intent;
     }

    //Lo que hace cada opción del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Otras_apps:
                startActivity(new Intent(ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Pedro+Santangelo") ) );
                return true;
            case R.id.Rate:
                startActivity(new Intent(ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.aabcdata.sound") ) );
                return true;
            case R.id.Salir:
                FlurryAgent.onEndSession(this);
                this.finish();
                return true;
            case R.id.Acerca:
                AlertDialog builder;
                try {
                    builder = AboutDialogBuilder.create(this);
                    builder.show();
                } catch (NameNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void playLocalAudio(int R1)throws Exception
    {
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R1);
        mediaPlayer.start();
    }


     @Override
     protected void onResume() {
         super.onResume();

         Presage.getInstance().adToServe("interstitial", new IADHandler() {

             @Override
             public void onAdNotFound() {

                 //Code to add Leadbolt
                 AppTracker.loadModule(getApplicationContext(), "inapp");
             }

             @Override
             public void onAdFound() {
                 Log.i("PRESAGE", "ad found");
             }

             @Override
             public void onAdClosed() {
                 Log.i("PRESAGE", "ad closed");
             }
         });
     }
    @Override
    public void onBackPressed ()
    {
        this.finish();
        System.exit(0);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onBackPressed();
    }

    @Override
    public void onStop()
    {
        FlurryAgent.onEndSession(this);
        if (isFinishing() || (mediaPlayer != null))  {
            System.exit(0);
            mediaPlayer.pause();
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onStop();
        //Cerrar del todo la aplicaci&oacute;n al Salir y evitar que suene cuando no debe
    }
    @Override
    public void onPause ()
    {
     if (this.isFinishing()) {
         this.finish();
         if (mediaPlayer != null) {
             mediaPlayer.pause();
             mediaPlayer.stop();
             mediaPlayer.release();
         }
     }
        super.onPause();
    }


    //Definicion de la interfaz de usuario
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Presage.getInstance().setContext(this.getBaseContext());
        Presage.getInstance().start();
        // para el share de la barra de menus
        Intent mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, "From me to you, this text is new.");
        

        if(savedInstanceState == null) {
            // Initialize Leadbolt SDK with your api key
            AppTracker.startSession(getApplicationContext(), getString(R.string.LeadBoltStr));
            AppTracker.loadModuleToCache(getApplicationContext(), "inapp");
        }
        // cache Leadbolt Ad without showing it
        AppTracker.loadModuleToCache(getApplicationContext(), "inapp");

        // call this when you want to display the Leadbolt Interstitial
        //AppTracker.loadModule(getApplicationContext(), "inapp");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Button pedounoPlayerBtn = (Button)findViewById(R.id.pedounoPlayerBtn);
        Button pedodosPlayerBtn = (Button)findViewById(R.id.pedodosPlayerBtn);
        Button pedotresPlayerBtn = (Button)findViewById(R.id.pedotresPlayerBtn);
        Button pedocuatroPlayerBtn = (Button)findViewById(R.id.pedocuatroPlayerBtn);
        Button pedocincoPlayerBtn = (Button)findViewById(R.id.pedocincoPlayerBtn);
        Button pedoseisPlayerBtn = (Button)findViewById(R.id.pedoseisPlayerBtn);
        Button pedosietePlayerBtn = (Button)findViewById(R.id.pedosietePlayerBtn);
        Button pedoochoPlayerBtn = (Button)findViewById(R.id.pedoochoPlayerBtn);
        Button pedonuevePlayerBtn = (Button)findViewById(R.id.pedonuevePlayerBtn);
        Button pedodiezPlayerBtn = (Button)findViewById(R.id.pedodiezPlayerBtn);
        Button pedooncePlayerBtn = (Button)findViewById(R.id.pedooncePlayerBtn);
        Button pedodocePlayerBtn = (Button)findViewById(R.id.pedodocePlayerBtn);
        Button pedotrecePlayerBtn = (Button)findViewById(R.id.pedotrecePlayerBtn);
        Button pedocatorcePlayerBtn = (Button)findViewById(R.id.pedocatorcePlayerBtn);
        Button pedoquincePlayerBtn = (Button)findViewById(R.id.pedoquincePlayerBtn);
        Button pedodieciseisPlayerBtn = (Button)findViewById(R.id.pedodieciseisPlayerBtn);
        Button pedodiecisietePlayerBtn = (Button)findViewById(R.id.pedodiecisietePlayerBtn);
        Button pedodieciochoPlayerBtn = (Button)findViewById(R.id.pedodieciochoPlayerBtn);
        Button pedodiecinuevePlayerBtn = (Button)findViewById(R.id.pedodiecinuevePlayerBtn);
        Button pedoveinteplayerBtn = (Button)findViewById(R.id.pedoveintePlayerBtn);
        Button pedoventiunoPlayerBtn = (Button)findViewById(R.id.pedoventiunoPlayerBtn);
        Button pedoventidosPlayerBtn = (Button)findViewById(R.id.pedoventidosPlayerBtn);
        Button pedoventitresPlayerBtn = (Button)findViewById(R.id.pedoventitresPlayerBtn);
        Button pedoventicuatroPlayerBtn = (Button)findViewById(R.id.pedoventicuatroPlayerBtn);


//Id de Flurry
        FlurryAgent.onStartSession(this, getString(R.string.FlurryStr));

//Lo que hace el botón "Pedouno"
        pedounoPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
// Este es el de la edición de Navidad  --> playLocalAudio(R.raw.fart11800);
// Este es el de la edición normal
                    playLocalAudio(R.raw.pedoenaseo);
                    //AppTracker.loadModuleToCache(getApplicationContext(), "inapp");
                    //AppTracker.loadModule(getApplicationContext(),"inapp");

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedodos"
        pedodosPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fartw1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedotres"
        pedotresPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart11786);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedocuatro"
        pedocuatroPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart7);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedocinco"
        pedocincoPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart10);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedoseis"
        pedoseisPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fartw3);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedosiete"
        pedosietePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    //Este era el original lo cambio por el pedoocho... lo vuelvo a dejar igual
                    playLocalAudio(R.raw.fartw4);
                    //playLocalAudio(R.raw.fartw8);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedoocho"
        pedoochoPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    //Este era el original y se cambi� pior pedosiete... se deja como estaba
                    playLocalAudio(R.raw.fartw8);
                    //playLocalAudio(R.raw.fartw4);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedonueve"
        pedonuevePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fartwav1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedodiez"
        pedodiezPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fartwav2);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedoonce"
        pedooncePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fartwav3);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedodoce"
        pedodocePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fartwav4);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedotrece"
        pedotrecePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedocatorce"
        pedocatorcePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart2);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedoquince"
        pedoquincePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart8);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedodieciseis"
        pedodieciseisPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart9);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedodiecisiete"
        pedodiecisietePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart3);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Lo que hace el botón "Pedodieciocho"
        pedodieciochoPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart11);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }});

        pedodiecinuevePlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart5);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        pedoveinteplayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart12);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        pedoventiunoPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        pedoventidosPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart1529);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        pedoventitresPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart6);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        pedoventicuatroPlayerBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view)
            {
//Se llama a la funcion playLocalAudio
                try{
                    playLocalAudio(R.raw.fart4);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }});

//Aqui se pondr�an m�s botones
    }

    @Override
    public void onInit(int arg0) {
        // TODO Auto-generated method stub

    }



    //Aqui se ponen nuevos m�todos
//Este di�logo es para contruir el diálogo de acerca de...
    public static class AboutDialogBuilder {
        public static AlertDialog create( Context context ) throws NameNotFoundException {
            // Try to load the a package matching the name of our own package
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String versionInfo = pInfo.versionName;

            String aboutTitle = String.format(context.getString(R.string.about) + " %s", context.getString(R.string.app_name));
            String versionString = String.format(context.getString(R.string.version) +" %s", versionInfo);
            String aboutText = context.getString(R.string.textoabout);

            // Set up the TextView
            final TextView message = new TextView(context);
            // We'll use a spannablestring to be able to make links clickable
            final SpannableString s = new SpannableString(aboutText);

            // Set some padding
            message.setPadding(5, 5, 5, 5);
            // Set up the final string
            message.setText(versionString + "\n\n" + s);
            // Now linkify the text
            Linkify.addLinks(message, Linkify.ALL);
            return new AlertDialog.Builder(context).setTitle(aboutTitle).setCancelable(true).setIcon(R.drawable.pedoicon).setPositiveButton(
                    context.getString(android.R.string.ok), null).setView(message).create();


        }
    }
}
