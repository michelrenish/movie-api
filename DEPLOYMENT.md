# Render.com Deployment Guide for Movie API

This guide will help you deploy the Movie API to **Render.com** with their **completely free tier**.

## ✅ Why Render.com?

- ✅ **100% Free** - No credit card required
- ✅ 750 hours/month free (plenty for demos)
- ✅ Auto-deploys from Git
- ✅ Custom domains supported
- ⚠️ Apps sleep after 15 minutes of inactivity (wakes on first request)

## Prerequisites

- Git installed on your machine
- GitHub account
- Render account (sign up at https://render.com)

---

## Step 1: Initialize Git Repository

If you haven't already, initialize a Git repository in your project:

```bash
cd d:\Freelance\movie-api
git init
git add .
git commit -m "Movie API with Swagger - Ready for Render deployment"
```

---

## Step 2: Push to GitHub

1. **Create a new repository on GitHub**
   - Go to https://github.com/new
   - Name it `movie-api` or any name you prefer
   - Keep it **public** (required for Render free tier)
   - **DO NOT** initialize with README, .gitignore, or license

2. **Push your code to GitHub**:
   ```bash
   git remote add origin https://github.com/YOUR_USERNAME/movie-api.git
   git branch -M main
   git push -u origin main
   ```

---

## Step 3: Deploy to Render

### Method A: Using render.yaml (Recommended - Easier)

1. **Sign up/Login to Render**
   - Go to https://render.com
   - Sign up with your GitHub account (easiest)

2. **Create a New Web Service**
   - Click "New +" → "Web Service"
   - Connect your GitHub account (if not already)
   - Select your `movie-api` repository

3. **Render will auto-detect the `render.yaml` file**
   - Click "Apply" when it shows the detected configuration
   - Review the settings:
     - **Name**: movie-api
     - **Environment**: Java
     - **Plan**: Free
     - **Build Command**: `mvn clean install -DskipTests`
     - **Start Command**: `java -jar target/movie-api-1.0.0.jar`

4. **Click "Create Web Service"**
   - Render will start building and deploying
   - First deployment takes 5-10 minutes

### Method B: Manual Configuration

If `render.yaml` isn't detected:

1. After selecting your repository, configure manually:
   - **Name**: `movie-api`
   - **Environment**: Select "Java"
   - **Build Command**: `mvn clean install -DskipTests`
   - **Start Command**: `java -jar target/movie-api-1.0.0.jar`
   - **Plan**: Select "Free"

2. **Add Environment Variables** (if needed):
   - `JAVA_VERSION`: 17
   - `MAVEN_VERSION`: 3.8.6

3. Click "Create Web Service"

---

## Step 4: Monitor Deployment

Watch the build logs in real-time:
- You'll see Maven downloading dependencies
- Compiling your application
- Creating the JAR file
- Starting the application

Once you see **"Your service is live"**, you're done! 🎉

---

## Step 5: Access Your Deployed API

Render provides a free subdomain URL like:
```
https://movie-api-xxxx.onrender.com
```

### Your Endpoints:

- **API Base**: `https://movie-api-xxxx.onrender.com/api/movies`
- **Swagger UI**: `https://movie-api-xxxx.onrender.com/swagger-ui.html`
- **OpenAPI Docs**: `https://movie-api-xxxx.onrender.com/api-docs`
- **Health Check**: `https://movie-api-xxxx.onrender.com/actuator/health`

---

## Step 6: Test Your Deployed API

### Using cURL:

```bash
# Replace YOUR_APP_URL with your actual Render URL

# Get all movies
curl https://movie-api-xxxx.onrender.com/api/movies

# Add a new movie
curl -X POST https://movie-api-xxxx.onrender.com/api/movies \
  -H "Content-Type: application/json" \
  -d '{"title":"The Matrix","description":"A hacker discovers reality","releaseYear":1999,"genre":"Sci-Fi","rating":8.7}'

# Get movie by ID
curl https://movie-api-xxxx.onrender.com/api/movies/1
```

### Using Swagger UI:

Simply open `https://movie-api-xxxx.onrender.com/swagger-ui.html` in your browser and test interactively!

**Note**: First request after app sleeps (~15 min inactivity) may take 30-60 seconds to wake up.

---

## Configuration Files

The following files enable Render deployment:

1. **`render.yaml`** - Render service configuration
2. **`application.properties`** - Dynamic port binding (`${PORT:8000}`)
3. **`pom.xml`** - Spring Boot Actuator for health checks

---

## Free Tier Details

### What's Included:
- ✅ **750 hours/month** of runtime (enough for always-on if low traffic)
- ✅ **100 GB bandwidth/month**
- ✅ **Automatic SSL certificates** (HTTPS)
- ✅ **Auto-deploys** from Git
- ✅ **Zero cost** - No credit card required

### Limitations:
- ⚠️ **Apps spin down after 15 minutes** of inactivity
- ⚠️ **First request after sleep**: 30-60 second delay (cold start)
- ⚠️ **512 MB RAM** limit
- ⚠️ **0.1 CPU** shares

**For a demo/portfolio project like this, the free tier is perfect!**

---

## Keeping Your App Awake (Optional)

If you want to reduce cold starts, you can set up a free uptime monitor:

### Option 1: UptimeRobot (Free)
1. Sign up at https://uptimerobot.com
2. Create a new HTTP monitor
3. Set URL to your health check: `https://your-app.onrender.com/actuator/health`
4. Set interval to 5 minutes
5. This pings your app regularly to keep it awake

### Option 2: Cron Job (Free)
Use a free cron service like https://cron-job.org to ping your API every 10 minutes.

**Note**: Even without these, the app works fine - just has a brief delay on first request after sleeping.

---

## Updating Your Deployment

To deploy updates:

```bash
git add .
git commit -m "Description of changes"
git push
```

Render automatically detects the push and redeploys your app!

---

## Custom Domain (Optional)

In the Render dashboard:
1. Go to your service settings
2. Click "Custom Domain"
3. Add your domain (e.g., `api.yourdomain.com`)
4. Follow DNS configuration instructions
5. Free SSL certificate automatically provisioned

---

## Monitoring and Logs

In the Render dashboard, you can:
- 📊 **View real-time logs**
- 📈 **Monitor resource usage**
- 🔄 **See deployment history**
- ⚙️ **Configure environment variables**
- 🔔 **Set up deploy notifications**

---

## Troubleshooting

### Build Fails
**Problem**: Maven build errors
**Solution**: 
- Check build logs in Render dashboard
- Ensure all files are committed to Git
- Verify `pom.xml` is valid

### App Crashes on Startup
**Problem**: Application won't start
**Solution**:
- Check the logs for error messages
- Ensure JAR file name matches in start command
- Verify Java 17 compatibility

### Port Binding Issues
**Problem**: "Failed to bind to port"
**Solution**:
- Render sets the `PORT` environment variable automatically
- Your app uses `${PORT:8000}` which handles this
- Should work automatically

### Slow First Request
**Problem**: First request takes 30-60 seconds
**Solution**:
- This is normal for free tier (cold start)
- App was sleeping and needs to wake up
- Consider using UptimeRobot if this is an issue

### Out of Memory
**Problem**: App crashes with OOM error
**Solution**:
- Free tier has 512MB RAM
- Your app should be fine
- If needed, optimize Java memory: add to start command:
  ```
  java -Xmx400m -Xms200m -jar target/movie-api-1.0.0.jar
  ```

---

## Render vs Railway Comparison

| Feature | Render Free | Railway Free |
|---------|-------------|--------------|
| **Cost** | 100% Free | $5/month credits |
| **Runtime** | 750 hrs/month | ~500 hrs |
| **Sleep** | After 15 min | After 30 min |
| **Cold Start** | 30-60 sec | ~30 sec |
| **Credit Card** | Not required | Not required |
| **Best For** | Demos, portfolios | Active development |

**For your use case (demo/portfolio), Render is the better choice!**

---

## Support Resources

- **Render Documentation**: https://render.com/docs
- **Community Forum**: https://community.render.com
- **Status Page**: https://status.render.com

---

## 🎉 Your API is Now Live!

Once deployed, you can:
- ✅ Share your live API URL with anyone
- ✅ Add it to your portfolio/resume
- ✅ Use Swagger UI for live demos
- ✅ Test from anywhere in the world

**Example portfolio description**:
> "Live Demo: [Movie API](https://movie-api-xxxx.onrender.com/swagger-ui.html) - RESTful API built with Spring Boot, featuring interactive Swagger documentation and deployed on Render."

---

**Your Movie API is ready for the world! 🚀**
