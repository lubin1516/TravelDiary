package com.ghllz.travel.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
			        	list.add(Covers);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			   }
			return list;
		}
		@Override
		protected void onPostExecute(List<Covers> result) {
		}
	}.execute();
}









}
