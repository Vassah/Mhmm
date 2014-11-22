package com.slazaro.mhmm;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Story extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.story, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
        	if (position + 1 == 4) return Page4Fragment.newInstance(position + 1);
        	else if (position + 1 == 6) return Page6Fragment.newInstance(position + 1);
        	else if (position + 1 == getCount() - 1) return PagePenultFragment.newInstance(position + 1);
        	else return StoryPageFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 8 total pages.
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                	return getString(R.string.title_section4).toUpperCase(l);
                case 4:
                	return getString(R.string.title_section5).toUpperCase(l);
                case 5:
                	return getString(R.string.title_section6).toUpperCase(l);
                case 6:
                	return getString(R.string.title_section7).toUpperCase(l);
                case 7:
                	return getString(R.string.title_end).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * Fragment containing the story pages
     */
    public static class StoryPageFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        protected static final String ARG_SECTION_NUMBER = "fragment_page";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        //Need some way to make this return other sections than 
        public static StoryPageFragment newInstance(int sectionNumber) {
            StoryPageFragment fragment = new StoryPageFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public StoryPageFragment() {
        }
        
        int pageOf(int i) {
        	switch (i) {
        	    case 1: return R.layout.fragment_page_1;
        	    case 2: return R.layout.fragment_page_2;
        	    case 3: return R.layout.fragment_page_3;
        	    case 5: return R.layout.fragment_page_5;
        	    default: return R.layout.fragment_page_end;
        	}
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_page_1, container, false);
            if (getArguments().getInt(ARG_SECTION_NUMBER) > 1) {
            	int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
                View laterView = inflater.inflate(pageOf(sectionNumber), container, false);
                return laterView;
            }
            return rootView;
        }
    }
    
    //Special page fragment for page 4 implementing a Button
    public static class Page4Fragment extends StoryPageFragment implements View.OnClickListener {
    	//This is very wet i.e. unDRY. But not sure how to avoid it given
    	//the requirements of the type system i.e. the button
    	public static Page4Fragment newInstance(int sectionNumber) {
			Page4Fragment page = new Page4Fragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			page.setArguments(args);
    		return page;
    	}
    	
    	@Override
        public void onClick(View v) {
    		//Getting null out of this findViewById, so our view does not have it!
    		//The view passed in to onClick /is the clicked view/
        	TextView resultDisp = (TextView) getView().findViewById(R.id.result_disp);
        	if (resultDisp != null) {
        	    resultDisp.setText("Just kidding it doesn't do anything.");
        	} else {
        		Log.e("Page4Fragment.onClick", "v.findViewById return null");
        	}
        }
    	
    	@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        		Bundle savedInstanceState) {
        	View view = inflater.inflate(R.layout.fragment_page_4, container, false);
        	Button pointlessButton = (Button) view.findViewById(R.id.page_4_button);
        	pointlessButton.setOnClickListener(this);
        	return view;
        }
    }

    //Special page fragment implementing a count down timer of three seconds
    public static class Page6Fragment extends StoryPageFragment {
    	CountDownTimer timer;
    	TextView display;
    	public static Page6Fragment newInstance(int sectionNumber) {
    		Page6Fragment page = new Page6Fragment();
    		return page;
    	}
    	@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        		Bundle savedInstanceState) {
    		View v = inflater.inflate(R.layout.fragment_page_6, container, false);
    		display = (TextView) v.findViewById(R.id.timer_disp);
    		//Anonymous class describing the timer
    		timer  = new CountDownTimer(3000, 100) {
    			@Override
    			public void onTick(long millisUntilFinished) {
    				display.setText(String.valueOf(millisUntilFinished / 1000) +
    						" . " +
    		                String.valueOf((millisUntilFinished / 100) % 10));
    			}
    			@Override
    			public void onFinish() {
    				display.setText("0.0");
    			}
    		};
    		timer.start();
    		return v;
    	}
    }
    //Special page fragment implementing a count down to next birthday
    public static class PagePenultFragment extends StoryPageFragment {
    	TextView timerDisp;
    	CountDownTimer timer;
    	public static PagePenultFragment newInstance(int sectionNumber) {
    		PagePenultFragment page = new PagePenultFragment();
    		return page;
    	}
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
    			Bundle savedInstanceState) {
    		View v = inflater.inflate(R.layout.fragment_page_7, container, false);
    		timerDisp = (TextView) v.findViewById(R.id.next_bd_disp);
    		//Current time
    		GregorianCalendar now = new GregorianCalendar();
    		//Time for next birthday
    		GregorianCalendar nextBD = new GregorianCalendar(now.get(Calendar.YEAR) + 1, 
    				                                         10, 
    				                                         18);
    		//Anonymous class counting down to next birth day
    		timer = new CountDownTimer(nextBD.getTimeInMillis() - now.getTimeInMillis(), 1000) {
				@Override
				public void onTick(long millisUntilFinished) {
					long days = millisUntilFinished / (1000*60*60*24);
				    long hours = (millisUntilFinished / (1000*60*60)) % 24;
					long minutes = (millisUntilFinished / (1000*60)) % 60;
					long seconds = (millisUntilFinished / 1000) % 60;
                    timerDisp.setText(
                    		String.valueOf(days) + " days " +
                    		String.valueOf(hours) + " hours " +
                    		String.valueOf(minutes)+ " minutes and " +
                    		String.valueOf(seconds) + " seconds.");
				}
				@Override
				public void onFinish() {
					timerDisp.setText("HAPPY BIRTHDAY!");		
				}
    		};
    		timer.start();
    		return v;
    	}
    }
}
