package com.example.vxtuyen.firstopengl;

import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.opengles.GL10;


public class MainActivity extends ActionBarActivity {

    private GLSurfaceView glView;
    private Triangle triangle1;
    private Triangle triangle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.glView = new GLSurfaceView(this);
        glView.setRenderer(new MyOpenGLRender());
        setContentView(glView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    class MyOpenGLRender implements GLSurfaceView.Renderer{
        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height){
            Log.d("MyOpenGLRenderer","onSurfaceChanged: width="+width+", height="+height);
            triangle1 = new Triangle(0.5f, 1, 0, 0);
            triangle2 = new Triangle(0.5f, 0, 1, 0);
            gl.glViewport(0,0,width,height);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl, 45.0f, (float)width/(float)height, 0.1f, 100.0f);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }

        @Override
        public void onSurfaceCreated(GL10 gl, javax.microedition.khronos.egl.EGLConfig config){
            Log.d("MyOpenGLRenderer", "onSurfaceCreated");
        }

        @Override
        public void onDrawFrame(GL10 gl){
                gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
                gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
                gl.glLoadIdentity();
                gl.glTranslatef(0.0f, 0.0f, -5f);
                triangle1.draw(gl);
                gl.glTranslatef(2f, 0f, -5f);
                triangle2.draw(gl);

        }
    }
}



