document.addEventListener('DOMContentLoaded', function() {
    // Initialize Calendar
		const calendarEl = document.getElementById('calendar');
		if (calendarEl) {
		    const calendar = new FullCalendar.Calendar(calendarEl, {
		        initialView: 'dayGridMonth',
		        headerToolbar: {
		            left: 'prev',
		            center: 'title',
		            right: 'next today'
		        },
		        height: 'auto', // Very important - allows calendar to size to container
		        contentHeight: 'auto',
		        aspectRatio: 1.35, // Controls the shape/dimensions of the calendar
		        dayMaxEvents: true, // Allow "more" link when too many events
		        firstDay: 1, // Monday
		        fixedWeekCount: false, // Don't force 6 rows
		        dayHeaders: true,
		        weekNumbers: false,
		        navLinks: false, // Simplify UI
		        buttonText: {
		            today: 'today'
		        }
		    });
		    calendar.render();
		}

    // Tab Functionality
    const tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => {
        tab.addEventListener('click', function() {
            const tabId = this.getAttribute('data-tab');
            
            // Remove active class from all tabs
            tabs.forEach(t => t.classList.remove('active'));
            
            // Add active class to current tab
            this.classList.add('active');
            
            // Hide all content
            document.querySelectorAll('.lecture-content').forEach(content => {
                content.style.display = 'none';
            });
            
            // Show current content
            document.getElementById(tabId).style.display = 'block';
        });
    });

    // Mobile menu toggle
    const menuToggle = document.querySelector('.menu-toggle');
    const sidebar = document.querySelector('.sidebar');
    
    if (menuToggle && sidebar) {
        menuToggle.addEventListener('click', function() {
            sidebar.classList.toggle('show');
        });
    }

    // Notifications dropdown
    const notificationIcon = document.querySelector('.notification');
    if (notificationIcon) {
        notificationIcon.addEventListener('click', function() {
            // Implement notification dropdown functionality
            console.log('Notification clicked');
        });
    }

    // More options menu
    const moreOptions = document.querySelector('.more-options');
    if (moreOptions) {
        moreOptions.addEventListener('click', function() {
            // Implement more options dropdown functionality
            console.log('More options clicked');
        });
    }

    // Month selector
    const prevMonth = document.querySelector('.month-arrows .fa-chevron-left');
    const nextMonth = document.querySelector('.month-arrows .fa-chevron-right');
    const currentMonth = document.querySelector('.current-month');
    
    if (prevMonth && nextMonth && currentMonth && calendar) {
        prevMonth.addEventListener('click', function() {
            calendar.prev();
            updateCurrentMonth();
        });
        
        nextMonth.addEventListener('click', function() {
            calendar.next();
            updateCurrentMonth();
        });
        
        function updateCurrentMonth() {
            const date = calendar.getDate();
            const month = date.toLocaleString('default', { month: 'long' });
            const year = date.getFullYear();
            currentMonth.textContent = `${month} ${year}`;
        }
        
        // Initialize current month
        updateCurrentMonth();
    }

    // Add event button
    const addEventBtn = document.querySelector('.add-event');
    if (addEventBtn) {
        addEventBtn.addEventListener('click', function() {
            // Implement add event functionality
            console.log('Add event clicked');
        });
    }
});